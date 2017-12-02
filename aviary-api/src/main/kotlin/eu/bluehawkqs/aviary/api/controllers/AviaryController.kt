package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.di.AviaryComponent
import javax.servlet.ServletContext
import javax.ws.rs.Consumes
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.SecurityContext

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
abstract class AviaryController(context: ServletContext) {
    @Context private lateinit var securityContext: SecurityContext
    val di = context.getAttribute("aviaryComponent") as AviaryComponent

    /**
     * This is non-null for routes decorated with @Secured
     */
    protected val currentUserId: String?
        get() = securityContext.userPrincipal.name
}