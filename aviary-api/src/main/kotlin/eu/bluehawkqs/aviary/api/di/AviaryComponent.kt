package eu.bluehawkqs.aviary.api.di

import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Component
import eu.bluehawkqs.aviary.api.dao.TournamentsDao
import eu.bluehawkqs.aviary.api.dao.UsersDao
import eu.bluehawkqs.aviary.api.models.SerialisationModule
import java.util.Properties

@Component(modules = arrayOf(
        AviaryModule::class,
        DatabaseModule::class,
        ConfigModule::class,
        SerialisationModule::class))
interface AviaryComponent {
    fun authorName(): String
    fun usersDao(): UsersDao
    fun tournamentsDao(): TournamentsDao
    fun getJsonMapper(): ObjectMapper
    fun config(): Properties
}