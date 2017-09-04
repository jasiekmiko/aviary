package eu.bluehawkqs.aviary.api.di

import dagger.Module
import dagger.Provides

@Module
class AviaryModule {
    @Provides
    fun authorProvider() : String {
        return "jan"
    }
}