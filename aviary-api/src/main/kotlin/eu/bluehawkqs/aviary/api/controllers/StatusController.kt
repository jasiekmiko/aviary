package eu.bluehawkqs.aviary.api.controllers

import javax.servlet.ServletContext
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType


@Path("status")
class StatusController (@Context context:ServletContext): AviaryController(context) {
    private var authorName = di.authorName()
    private var env = di.config().getProperty("env")

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun doGet(): String {
        return "Status: OK\n" +
                "Author: $authorName\n" +
                "Environment: $env\n"
    }
}
