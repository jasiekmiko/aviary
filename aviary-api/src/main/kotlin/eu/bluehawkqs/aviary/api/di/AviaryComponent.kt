package eu.bluehawkqs.aviary.api.di

import dagger.Component
import eu.bluehawkqs.aviary.api.dao.UsersDao

@Component(modules = arrayOf(AviaryModule::class, DatabaseModule::class))
interface AviaryComponent {
    fun authorName(): String
    fun usersDao(): UsersDao
}