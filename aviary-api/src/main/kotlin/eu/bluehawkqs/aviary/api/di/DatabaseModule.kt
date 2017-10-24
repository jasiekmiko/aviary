package eu.bluehawkqs.aviary.api.di

import dagger.Module
import dagger.Provides
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import com.zaxxer.hikari.HikariDataSource
import com.zaxxer.hikari.HikariConfig
import java.util.*
import javax.sql.DataSource

@Module
class DatabaseModule {
    @Provides fun database(properties: Properties) : DataSource {
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
