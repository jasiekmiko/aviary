package eu.bluehawkqs.aviary.api.controllers

import javax.servlet.ServletContext
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Context


@Path("status")
class StatusController (@Context context:ServletContext): AviaryController(context) {
    private var authorName = di.authorName()
    private var env = di.config().getProperty("env")

    @GET
    fun doGet(): String {
        return "Status: OK\n" +
                "Author: $authorName\n" +
                "Environment: $env\n"
    }
}
