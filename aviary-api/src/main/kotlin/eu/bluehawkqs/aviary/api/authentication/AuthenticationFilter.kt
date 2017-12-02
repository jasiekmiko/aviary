package eu.bluehawkqs.aviary.api.authentication

import java.security.Principal
import javax.annotation.Priority
import javax.ws.rs.Priorities
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Response
import javax.ws.rs.core.SecurityContext
import javax.ws.rs.ext.Provider

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
class AuthenticationFilter : ContainerRequestFilter {

    override fun filter(requestContext: ContainerRequestContext) {
        val token = validateAndGetToken(requestContext)
        if (token == null) {
            abortWithUnauthorized(requestContext)
            return
        }

        try {
            val claims = FirebaseAuth.verifyIdToken(token)
            injectUserIdIntoSecurityContext(requestContext, claims)
        } catch (e: Exception) {
            // TODO LOG the exception
            abortWithUnauthorized(requestContext)
        }
    }

    private fun injectUserIdIntoSecurityContext(requestContext: ContainerRequestContext, claims: FirebaseClaims) {
        val currentSecurityContext = requestContext.securityContext
        requestContext.securityContext = object : SecurityContext {
            override fun getUserPrincipal(): Principal {

                return Principal { claims.uid }
            }

            override fun isUserInRole(role: String): Boolean {
                return true
            }

            override fun isSecure(): Boolean {
                return currentSecurityContext.isSecure
            }

            override fun getAuthenticationScheme(): String {
                return AUTHENTICATION_SCHEME
            }
        }
    }

    private fun validateAndGetToken(requestContext: ContainerRequestContext): String? {
        val authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION)
        return when {
            validSchemeFormat(authorizationHeader) -> authorizationHeader.substringAfter(AUTHENTICATION_SCHEME).trim()
            else                                   -> null
        }
    }

    private fun validSchemeFormat(authorizationHeader: String?): Boolean {
        return authorizationHeader != null
            && authorizationHeader.startsWith(AUTHENTICATION_SCHEME + " ", true)
    }

    private fun abortWithUnauthorized(requestContext: ContainerRequestContext) {
        val response = Response.status(Response.Status.UNAUTHORIZED).build()
        requestContext.abortWith(response)
    }

    companion object {
        private val AUTHENTICATION_SCHEME = "Bearer"
    }
}