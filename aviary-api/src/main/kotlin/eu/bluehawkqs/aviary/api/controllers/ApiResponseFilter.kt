package eu.bluehawkqs.aviary.api.controllers

import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter

@Suppress("unused") // It's registered as a filter in web.xml
class ApiResponseFilter : ContainerResponseFilter {
    override fun filter(requestContext: ContainerRequestContext, responseContext: ContainerResponseContext) {
        responseContext.headers.apply {
            add("Access-Control-Allow-Origin", "*")
            add("Access-Control-Allow-Headers", "Authorization")
            add("Access-Control-Allow-Headers", "Content-Type")
        }
    }
}