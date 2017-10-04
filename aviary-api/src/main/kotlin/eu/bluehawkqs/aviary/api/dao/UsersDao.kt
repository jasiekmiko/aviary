package eu.bluehawkqs.aviary.api.dao

import eu.bluehawkqs.aviary.api.dao.aviary.tables.Persons.PERSONS
import eu.bluehawkqs.aviary.api.dao.aviary.tables.Users.USERS
import eu.bluehawkqs.aviary.api.dao.aviary.tables.records.UsersRecord
import eu.bluehawkqs.aviary.api.models.AviaryUser
import eu.bluehawkqs.aviary.api.models.Person
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import javax.inject.Inject
import javax.sql.DataSource


class UsersDao @Inject constructor(private val ds: DataSource) {
    fun getAll(): List<AviaryUser> {
        ds.connection.use { conn ->
            val create = DSL.using(conn, SQLDialect.MYSQL)
            return create
                    .select()
                    .from(USERS)
                    .join(PERSONS).on(USERS.PERSON_ID.eq(PERSONS.ID))
                    .fetch().map {
                AviaryUser(it[USERS.FIREBASE_ID],
                        it[USERS.EMAIL],
                        Person(it[PERSONS.ID],
                                it[PERSONS.FIRST_NAME],
                                it[PERSONS.LAST_NAME],
                                it[PERSONS.DOB].toLocalDateTime().toLocalDate(),
                                it[PERSONS.GENDER]
                        ))
            }
        }
    }

    fun addUser(aviaryUser: AviaryUser) {
        ds.connection.use { conn ->
            val create = DSL.using(conn, SQLDialect.MYSQL)
            create.transaction { config ->
                val newPerson = DSL.using(config).newRecord(PERSONS, aviaryUser.person)
                newPerson.store()
                DSL.using(config).insertInto(USERS)
                        .set(UsersRecord(aviaryUser.firebaseId, newPerson.id, aviaryUser.email))
                        .execute()
            }
        }
    }

}