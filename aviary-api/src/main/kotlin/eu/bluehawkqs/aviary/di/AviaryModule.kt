package eu.bluehawkqs.aviary.di

import dagger.Module
import dagger.Provides

@Module
class AviaryModule {
    @Provides
    fun authorProvider() : String {
        return "jan"
    }
}