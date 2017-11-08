package eu.bluehawkqs.aviary.api.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import javax.ws.rs.ext.ContextResolver
import javax.ws.rs.ext.Provider

@Provider
class JacksonProvider : ContextResolver<ObjectMapper> {
    private val defaultObjectMapper = jacksonObjectMapper()
            .apply { registerModule(JavaTimeModule()) }

    override fun getContext(p0: Class<*>?): ObjectMapper {
        return defaultObjectMapper
    }

}