package eu.bluehawkqs.aviary.api.di

import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Component
import eu.bluehawkqs.aviary.api.dao.UsersDao
import eu.bluehawkqs.aviary.api.models.SerialisationModule

@Component(modules = arrayOf(
        AviaryModule::class,
        DatabaseModule::class,
        SerialisationModule::class))
interface AviaryComponent {
    fun authorName(): String
    fun usersDao(): UsersDao
    fun getJsonMapper(): ObjectMapper
}