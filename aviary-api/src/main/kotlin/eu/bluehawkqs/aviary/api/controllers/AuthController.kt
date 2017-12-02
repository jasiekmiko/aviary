package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.authentication.Secured
import java.io.IOException
import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Context

@Path("auth")
@Secured
class AuthController(@Context context: ServletContext) : AviaryController(context) {
    @GET
    @Throws(ServletException::class, IOException::class)
    fun doGet(): String {
        return """{"userId": "$currentUserId"}"""
    }
}
