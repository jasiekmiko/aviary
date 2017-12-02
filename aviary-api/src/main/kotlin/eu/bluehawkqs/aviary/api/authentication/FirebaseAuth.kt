package eu.bluehawkqs.aviary.api.authentication

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureException
import java.io.ByteArrayInputStream
import java.io.UnsupportedEncodingException
import java.security.PublicKey
import java.security.cert.CertificateFactory
import javax.security.cert.CertificateException

// Adapted from https://gist.github.com/feroult/92fe8764d1dddcb3599397e5f44140f9
// First attempt to use the Firebase SDK to validate the tokens has failed, since it uses long-running threads and is
// therefore not allowed on automatically scaled GAE instances.
object FirebaseAuth {

    private val APP_ID = "aviary-130922"

    fun verifyIdToken(token: String): FirebaseClaims {
        for ((keyId, key) in GooglePublicKeys.getKeys()) {
            try {
                val claimsJws = verifyAgainstPublicKey(token, keyId, key)
                return FirebaseClaims(claimsJws.body)
            } catch (e: SignatureException) {
                // try next key
            } catch (e: ExpiredJwtException) {
                throw InvalidFirebaseTokenException(e.message!!)
            }
        }
        throw InvalidFirebaseTokenException("No Google public keys for JWT Token")
    }

    private fun verifyAgainstPublicKey(token: String, keyId: String, publicKey: String): Jws<Claims> {
        val claimsJws = Jwts.parser()
            .setSigningKey(createPk(publicKey))
            .parseClaimsJws(token)
        validateClaims(claimsJws, keyId)
        return claimsJws
    }

    private fun validateClaims(claimsJws: Jws<Claims>, keyId: String) {
        if (claimsJws.header.getAlgorithm() == "RS256" &&
            claimsJws.body.audience == APP_ID &&
            claimsJws.body.issuer == "https://securetoken.google.com/" + APP_ID &&
            claimsJws.body.subject != null &&
            claimsJws.header.getKeyId() == keyId) {
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