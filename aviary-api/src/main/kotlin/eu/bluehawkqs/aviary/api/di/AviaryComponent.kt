package eu.bluehawkqs.aviary.api.di

import dagger.Component
import eu.bluehawkqs.aviary.api.dao.PlayersDao
import eu.bluehawkqs.aviary.api.dao.TournamentsDao
import eu.bluehawkqs.aviary.api.dao.UsersDao
import java.util.Properties

@Component(modules = arrayOf(
        AviaryModule::class,
        DatabaseModule::class,
        ConfigModule::class))
interface AviaryComponent {
    fun authorName(): String
    fun usersDao(): UsersDao
    fun tournamentsDao(): TournamentsDao
    fun playersDao(): PlayersDao
    fun config(): Properties
}