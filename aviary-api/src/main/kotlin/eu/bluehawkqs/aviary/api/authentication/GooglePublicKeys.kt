package eu.bluehawkqs.aviary.api.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.appengine.api.urlfetch.URLFetchServiceFactory
import java.io.IOException
import java.net.URL

class GooglePublicKeys private constructor() {

    private var keys = fetchGooglePublicKeys()
    private var timestamp = System.currentTimeMillis()

    companion object {
        const private val GOOGLE_PUBLIC_KEYS = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com"
        const private val ONE_DAY_IN_MILLISECONDS = 1000 * 60 * 60 * 24

        fun getKeys(): Map<String, String> {
            return instance.keys
        }

        private val minimumFreshness
            get() = System.currentTimeMillis() - ONE_DAY_IN_MILLISECONDS

        private var instance = GooglePublicKeys()
            get() {
                if (field.timestamp < minimumFreshness) {
                    field = GooglePublicKeys()
                }
                return field
            }

        private fun fetchGooglePublicKeys(): Map<String, String> {
            return parseJson(fetchKeysJson())
        }

        private fun fetchKeysJson(): String {
            try {
                val urlFetch = URLFetchServiceFactory.getURLFetchService()
                val response = urlFetch.fetch(URL(GOOGLE_PUBLIC_KEYS))
                return String(response.content)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }

        private fun parseJson(json: String): Map<String, String> {
            val map = hashMapOf<String, String>()
            return ObjectMapper().readValue(json, map.javaClass)
        }
    }

}