package eu.bluehawkqs.aviary.di

import dagger.Component
import eu.bluehawkqs.aviary.dao.DbConnectionManager
import eu.bluehawkqs.aviary.dao.PlayersDao
import eu.bluehawkqs.aviary.dao.TournamentsDao
import eu.bluehawkqs.aviary.dao.UsersDao
import java.util.*
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AviaryModule::class,
        DatabaseModule::class,
        ConfigModule::class))
interface AviaryComponent {
    fun authorName(): String
    fun dbConnectionManager(): DbConnectionManager
    fun usersDao(): UsersDao
    fun tournamentsDao(): TournamentsDao
    fun playersDao(): PlayersDao
    fun config(): Properties
}