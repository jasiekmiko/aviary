package eu.bluehawkqs.aviary.dao

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import javax.inject.Inject
import javax.sql.DataSource

class DbConnectionManager @Inject constructor(private val ds: DataSource) {
    fun <ReturnType> run(block: (create: DSLContext) -> ReturnType): ReturnType {
        ds.connection.use {
            val create = DSL.using(it, SQLDialect.MYSQL)
            return block(create)
        }
    }

}