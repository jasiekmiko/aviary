package eu.bluehawkqs.aviary.api.firebase

import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener

class FirebaseContextListener : ServletContextListener {
    override fun contextInitialized(sce: ServletContextEvent) {
        //TODO vary by environment?
        val firebaseCredentials = sce.servletContext.getResourceAsStream("/WEB-INF/Aviary-firebase-credentials.json")
        val options = FirebaseOptions.Builder()
                .setServiceAccount(firebaseCredentials)
                .build()
        FirebaseApp.initializeApp(options)
    }

    override fun contextDestroyed(p0: ServletContextEvent?) {
    }

}