package eu.bluehawkqs.aviary.api.dao

import eu.bluehawkqs.aviary.api.dao.aviary.tables.Users.USERS
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.Connection
import java.sql.SQLException
import javax.inject.Inject


class UserDao @Inject constructor(private val conn: Connection) {
    fun getAll(): List<AviaryUser> {
        try {
            val context = DSL.using(conn, SQLDialect.H2)
            context.insertInto(USERS, USERS.FIRST_NAME, USERS.LAST_NAME)
                    .values("Jon", "Snow")
                    .execute()
            val result = context.selectFrom(USERS).fetch()
            return result.map {
                AviaryUser(it.firstName, it.lastName)
            }
        } catch (e: SQLException) {

        }
        return emptyList()
    }

    fun addUser(aviaryUser: AviaryUser) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

data class AviaryUser(val firstName: String, val lastName: String)
