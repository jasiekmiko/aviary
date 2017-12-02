package eu.bluehawkqs.aviary.api

import com.google.appengine.api.utils.SystemProperty
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter

class ApiResponseFilter : ContainerResponseFilter {
    override fun filter(requestContext: ContainerRequestContext, responseContext: ContainerResponseContext) {
        responseContext.headers.apply {
            add("Access-Control-Allow-Origin", when(SystemProperty.environment.value()) {
                SystemProperty.Environment.Value.Production -> "http://aviary.bluehawkqs.eu"
                SystemProperty.Environment.Value.Development -> "http://localhost:4200"
                else -> throw Exception("Environment not recognised")
            })
            add("Access-Control-Allow-Headers", "Authorization")
            add("Access-Control-Allow-Headers", "Content-Type")
        }
    }
}

// TODO Add exception catcher. Currently if response terminates with an exception then no headers are applied.