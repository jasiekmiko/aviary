package eu.bluehawkqs.aviary.api.di

import dagger.Module
import dagger.Provides
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import java.sql.Connection
import java.sql.DriverManager

@Module
class DatabaseModule {
    // TODO this needs to change to give access to a connectionPool or similar, not to a single persistent connection
    @Provides fun database() : Connection {
        val dbUrl = "jdbc:mysql://localhost:3306/"
        val conn = DriverManager.getConnection(dbUrl, "root", "dev")
        val dbConnection = JdbcConnection(conn)
        val database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(dbConnection)
        database.defaultSchemaName = "AVIARY"
        val liquibase = Liquibase("db/db-changelog.xml", ClassLoaderResourceAccessor(), database)
        liquibase.update("")
        return conn
    }
}
