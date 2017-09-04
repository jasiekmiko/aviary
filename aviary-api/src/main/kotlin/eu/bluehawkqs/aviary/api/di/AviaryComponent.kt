package eu.bluehawkqs.aviary.api.di

import dagger.Component

@Component(modules = arrayOf(AviaryModule::class))
interface AviaryComponent {
    fun authorName(): String
}