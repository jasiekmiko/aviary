package eu.bluehawkqs.aviary.api.di

import com.google.appengine.api.utils.SystemProperty
import com.google.appengine.api.utils.SystemProperty.Environment.Value.*
import dagger.Module
import dagger.Provides
import org.cfg4j.provider.ConfigurationProviderBuilder
import org.cfg4j.source.classpath.ClasspathConfigurationSource
import org.cfg4j.source.context.environment.ImmutableEnvironment
import java.nio.file.Paths
import java.util.*
import javax.inject.Singleton


@Module
class ConfigModule {
    @Provides @Singleton
    fun configProvider(): Properties {
        val configFilesProvider = { arrayListOf(Paths.get("application.properties")) }
        val source = ClasspathConfigurationSource(configFilesProvider)
        val env = when (SystemProperty.environment.value()) {
            Production -> "prod"
            Development -> "dev"
            else -> throw Exception("Environment not recognised")
        }

        return ConfigurationProviderBuilder()
                .withConfigurationSource(source)
                .withEnvironment(ImmutableEnvironment("config/$env/"))
                .build()
                .allConfigurationAsProperties()
    }
}