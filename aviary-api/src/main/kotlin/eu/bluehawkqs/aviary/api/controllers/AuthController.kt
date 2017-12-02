package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.services.AppengineFirebaseAuth
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
        return if (authHeader == null) {
            "Empty auth token received"
        } else {
            val token = authHeader!!.substringAfter("Bearer ")
            val uid = AppengineFirebaseAuth.verifyIdToken(token).uid
            """{"userId": "$uid"}"""
        }
    }
}
