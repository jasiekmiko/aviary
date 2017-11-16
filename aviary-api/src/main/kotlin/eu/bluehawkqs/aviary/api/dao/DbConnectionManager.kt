package eu.bluehawkqs.aviary.api.dao

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import javax.inject.Inject
import javax.sql.DataSource

class DbConnectionManager @Inject constructor(private val ds: DataSource) {
    fun <ReturnType> run(body: (create: DSLContext) -> ReturnType): ReturnType {
        ds.connection.use {
            val create = DSL.using(it, SQLDialect.MYSQL)
            return body(create)
        }
    }

}