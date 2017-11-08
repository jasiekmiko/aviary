package eu.bluehawkqs.aviary.api.controllers

import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter

class ApiResponseFilter : ContainerResponseFilter {
    override fun filter(requestContext: ContainerRequestContext, responseContext: ContainerResponseContext) {
        responseContext.headers.apply {
            // TODO Investigate setting up a proxy in dev angular to remove all cross-origin stuff. either way tighten this up
            add("Access-Control-Allow-Origin", "*")
            add("Access-Control-Allow-Headers", "Authorization")
            add("Access-Control-Allow-Headers", "Content-Type")
        }
    }
}