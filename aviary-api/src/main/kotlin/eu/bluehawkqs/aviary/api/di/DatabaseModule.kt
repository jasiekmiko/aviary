package eu.bluehawkqs.aviary.api.di

import dagger.Module
import dagger.Provides
import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import java.sql.Connection
import java.sql.DriverManager

@Module
class DatabaseModule {
    // TODO this needs to change to give access to a connectionPool or similar, not to a single persistent connection
    @Provides fun database() : Connection {
        val dbUrl = "jdbc:h2:mem:dev;DB_CLOSE_DELAY=-1;MODE=MySQL;DATABASE_TO_UPPER=FALSE;IGNORECASE=TRUE;INIT=create schema if not exists aviary"
        val conn = DriverManager.getConnection(dbUrl, "sa", "")
        val dbConnection = JdbcConnection(conn)
        val liquibase = Liquibase("db/db-changelog.xml", ClassLoaderResourceAccessor(), dbConnection)
        liquibase.update("")
        return conn
    }
}
