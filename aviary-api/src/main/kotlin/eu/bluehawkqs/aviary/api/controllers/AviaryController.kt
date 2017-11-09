package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.di.AviaryComponent
import javax.servlet.ServletContext
import javax.ws.rs.Consumes
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
abstract class AviaryController(context: ServletContext) {
    val di = context.getAttribute("aviaryComponent") as AviaryComponent
}