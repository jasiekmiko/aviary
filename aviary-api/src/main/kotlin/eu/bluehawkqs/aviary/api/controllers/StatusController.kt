package eu.bluehawkqs.aviary.api.controllers

import javax.servlet.ServletContext
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Context


@Path("status")
class StatusController (@Context context:ServletContext): AviaryController2(context) {
    private var authorName = aviaryComponent.authorName()
    private var env = aviaryComponent.config().getProperty("env")

    @GET
    fun doGet(): String {
        return "Status: OK\n" +
                "Author: $authorName\n" +
                "Environment: $env\n"
    }
}
