package eu.bluehawkqs.aviary.api.models

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dagger.Module
import dagger.Provides

@Module
class SerialisationModule {
    @Provides fun jsonMapper(): ObjectMapper {
        val mapper = jacksonObjectMapper()
        mapper.registerModule(JavaTimeModule())
        return mapper
    }
}