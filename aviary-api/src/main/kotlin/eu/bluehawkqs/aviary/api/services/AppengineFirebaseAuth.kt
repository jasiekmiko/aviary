package eu.bluehawkqs.aviary.api.services

import com.google.appengine.api.urlfetch.URLFetchServiceFactory
import com.google.gson.Gson
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureException
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URL
import java.security.PublicKey
import java.security.cert.CertificateFactory
import javax.security.cert.CertificateException


// Adapted from https://gist.github.com/feroult/92fe8764d1dddcb3599397e5f44140f9
// First attempt to use the Firebase SDK to validate the tokens has failed, since it uses long-running threads and is
// therefore not allowed on automatically scaled GAE instances.
// TODO clean this whole file up
object AppengineFirebaseAuth {

    private val APP_ID = "aviary-130922"

    fun verifyIdToken(token: String): AppengineFirebaseToken {
        val publicKeys = GooglePublicKeys.getKeys()

        for (kid in publicKeys.keys) {
            try {
                val claimsJws = verifyIdTokey(token, kid, publicKeys[kid]!!)
                return AppengineFirebaseToken(claimsJws.body)
            } catch (e: SignatureException) {
                // try next key
            } catch (e: ExpiredJwtException) {
                throw InvalidFirebaseTokenException(e.message!!)
            }
        }

        throw InvalidFirebaseTokenException("No Google public keys for JWT Token")
    }

    private fun verifyIdTokey(token: String, kid: String, publicKey: String): Jws<Claims> {
        val pk = createPk(publicKey)
        val claimsJws = Jwts.parser().setSigningKey(pk).parseClaimsJws(token)
        validate(claimsJws, kid)
        return claimsJws
    }

    private fun validate(claimsJws: Jws<Claims>, kid: String) {
        if (claimsJws.getHeader().getAlgorithm().equals("RS256") &&
            claimsJws.getBody().getAudience().equals(APP_ID) &&
            claimsJws.getBody().getIssuer().equals("https://securetoken.google.com/" + APP_ID) &&
            claimsJws.getBody().getSubject() != null &&
            claimsJws.getHeader().getKeyId().equals(kid)) {
            return
        }
        throw InvalidFirebaseTokenException("Invalid Firebase Id Token")
    }

    private fun createPk(publicKey: String): PublicKey {
        try {
            val cf = CertificateFactory.getInstance("X.509")
            val stream = ByteArrayInputStream(publicKey.toByteArray(charset("UTF-8")))
            val cert = cf.generateCertificate(stream)
            return cert.publicKey
        } catch (e: CertificateException) {
            throw RuntimeException(e)
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException(e)
        }

    }

}

class AppengineFirebaseToken(private val claims: Claims) {

    val uid: String
        get() = claims["user_id"] as String

    val issuer: String
        get() = claims["iss"] as String

    val name: String
        get() = claims["name"] as String

    val picture: String
        get() = claims["picture"] as String

    val email: String
        get() = claims["email"] as String

    val isEmailVerified: Boolean
        get() = java.lang.Boolean.valueOf(claims["email_verified"] as String)!!

}

class InvalidFirebaseTokenException(message: String) : RuntimeException(message)

class GooglePublicKeys private constructor() {

    private var timestamp: Long = 0

    private var keys: Map<String, String>? = null

    companion object {

        private val GOOGLE_PUBLIC_KEYS = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com"

        private val ONE_DAY = 1000 * 60 * 60 * 24

        private var instance: GooglePublicKeys? = null

        fun getKeys(): Map<String, String> {
            if (instance == null || isOutdated) {
                reload()
            }

            return instance!!.keys!!
        }

        private val isOutdated: Boolean
            get() = instance!!.timestamp < System.currentTimeMillis() - ONE_DAY

        @Synchronized private fun reload() {
            if (instance != null && !isOutdated) {
                return
            }

            instance = GooglePublicKeys()
            instance!!.keys = fetchGooglePublicKeys()
            instance!!.timestamp = System.currentTimeMillis()
        }

        private fun fetchGooglePublicKeys(): Map<String, String> {
            return parseJson(fetchKeysJson())
        }

        private fun fetchKeysJson(): String {
            try {
                val urlFetch = URLFetchServiceFactory.getURLFetchService()

                val response = urlFetch.fetch(URL(GOOGLE_PUBLIC_KEYS))
                return String(response.getContent())
            } catch (e: IOException) {
                throw RuntimeException(e)
            }

        }

        private fun parseJson(json: String): Map<String, String> {
            val gson = Gson()
            val map = hashMapOf<String, String>()
            return gson.fromJson(json, map.javaClass)
        }
    }

}