package eu.bluehawkqs.aviary.api.controllers

import com.google.firebase.auth.FirebaseAuth
import java.io.IOException
import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType


@Path("auth")
class AuthController(@Context context: ServletContext) : AviaryController(context) {
    @HeaderParam("Authorization")
    var authHeader: String? = null

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Throws(ServletException::class, IOException::class)
    fun doGet(): String {
        if (authHeader == null) {
            return "Empty auth token received"
        } else {
            val token = authHeader!!.substringAfter("Bearer ")
            // TODO implement proper async stuff
            val completedTask = {
                val task = FirebaseAuth
                        .getInstance()
                        .verifyIdToken(token)
                while (!task.isComplete) {
                    Thread.sleep(1000)
                }
                task
            }()
            return if (completedTask.isSuccessful) {
                "User ID:" + completedTask.result.uid
            } else {
                "Authentication failed: " + completedTask.exception
            }
        }
    }
}
