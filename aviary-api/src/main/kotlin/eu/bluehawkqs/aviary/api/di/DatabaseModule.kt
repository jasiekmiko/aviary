package eu.bluehawkqs.aviary.api.di

import dagger.Module
import dagger.Provides
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import com.zaxxer.hikari.HikariDataSource
import com.zaxxer.hikari.HikariConfig
import javax.sql.DataSource


@Module
class DatabaseModule {
    @Provides fun database() : DataSource {
        val config = HikariConfig()
        config.jdbcUrl = "jdbc:mysql://localhost:3306/"
        config.username = "root"
        config.password = "dev"
        val ds = HikariDataSource(config)
        val database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(ds.connection))
        database.defaultSchemaName = "AVIARY"
        val liquibase = Liquibase("db/db-changelog.xml", ClassLoaderResourceAccessor(), database)
        liquibase.update("")
        return ds
    }
}
