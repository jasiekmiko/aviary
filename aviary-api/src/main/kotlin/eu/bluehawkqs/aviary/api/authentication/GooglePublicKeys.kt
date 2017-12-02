package eu.bluehawkqs.aviary.api.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.appengine.api.urlfetch.URLFetchServiceFactory
import java.io.IOException
import java.net.URL

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
            val map = hashMapOf<String, String>()
            return ObjectMapper().readValue(json, map.javaClass)
        }
    }

}