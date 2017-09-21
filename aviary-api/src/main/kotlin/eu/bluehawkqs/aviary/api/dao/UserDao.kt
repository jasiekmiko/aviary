package eu.bluehawkqs.aviary.api.dao

import eu.bluehawkqs.aviary.api.dao.aviary.tables.Persons.PERSONS
import eu.bluehawkqs.aviary.api.dao.aviary.tables.Users.USERS
import eu.bluehawkqs.aviary.api.dao.aviary.tables.records.PersonsRecord
import eu.bluehawkqs.aviary.api.dao.aviary.tables.records.UsersRecord
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.Connection
import java.sql.SQLException
import java.time.LocalDate
import javax.inject.Inject


class UserDao @Inject constructor(private val conn: Connection) {
    fun getAll(): List<AviaryUser> {
        try {
            val create = DSL.using(conn, SQLDialect.MYSQL)
            return create
                    .select()
                    .from(USERS)
                    .join(PERSONS).on(USERS.PERSON_ID.eq(PERSONS.ID))
                    .fetch().map {
                AviaryUser(it[USERS.FIREBASE_ID], Person(
                        it[PERSONS.ID],
                        it[PERSONS.FIRST_NAME],
                        it[PERSONS.LAST_NAME],
                        it[PERSONS.DOB].toLocalDateTime().toLocalDate(),
                        it[PERSONS.GENDER]
                ))
            }
        } catch (e: SQLException) {

        }
        return emptyList()
    }

    fun addUser(aviaryUser: AviaryUser) {
        val create = DSL.using(conn, SQLDialect.MYSQL)
        create.transaction { config ->
            val newPerson = DSL.using(config).newRecord(PERSONS, aviaryUser.person)
            newPerson.store()
            DSL.using(config).insertInto(USERS)
                    .set(UsersRecord(aviaryUser.firebaseId, newPerson.id))
                    .execute()
        }
    }

}

data class AviaryUser(val firebaseId: String, val person: Person)

data class Person(val id: Int, val firstName: String, val lastName: String, val dob: LocalDate, val gender: String)
