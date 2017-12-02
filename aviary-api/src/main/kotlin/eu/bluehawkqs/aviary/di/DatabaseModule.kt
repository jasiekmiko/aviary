package eu.bluehawkqs.aviary.di

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dagger.Module
import dagger.Provides
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import java.util.*
import javax.inject.Singleton
import javax.sql.DataSource

@Module
class DatabaseModule {
    @Provides @Singleton fun database(properties: Properties) : DataSource {
        val config = HikariConfig()
        config.jdbcUrl = properties.getProperty("db.url")
        config.username = properties.getProperty("db.username")
        config.password = properties.getProperty("db.password")
        val ds = HikariDataSource(config)
        val database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(ds.connection))
        database.defaultSchemaName = "aviary"
        val liquibase = Liquibase("db/db-changelog.xml", ClassLoaderResourceAccessor(), database)
        liquibase.update("")
        return ds
    }
}
