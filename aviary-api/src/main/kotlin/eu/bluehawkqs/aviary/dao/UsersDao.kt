package eu.bluehawkqs.aviary.dao

import eu.bluehawkqs.aviary.api.models.AviaryUser
import eu.bluehawkqs.aviary.api.models.Person
import eu.bluehawkqs.aviary.dao.aviary.tables.Persons.PERSONS
import eu.bluehawkqs.aviary.dao.aviary.tables.Users.USERS
import eu.bluehawkqs.aviary.dao.aviary.tables.records.UsersRecord
import org.jooq.impl.DSL
import javax.inject.Inject

class UsersDao @Inject constructor(private val db: DbConnectionManager) {
    fun getAll(): List<AviaryUser> {
        return db.run {
            it.select()
                .from(USERS)
                .join(PERSONS).on(USERS.PERSON_ID.eq(PERSONS.ID))
                .fetch()
                .map {
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
        db.run {
            //TODO Move the transaction creation to DbConnectionManager
            it.transaction { config ->
                DSL.using(config).apply {
                    val newPerson = it.newRecord(PERSONS, aviaryUser.person)
                    newPerson.store()
                    it.insertInto(USERS)
                        .set(UsersRecord(aviaryUser.firebaseId, newPerson.id, aviaryUser.email))
                        .execute()
                }
            }
        }
    }

    fun getUserIdByFirebaseID(uid: String): Int {
        return db.run {
            it.fetchValue(
                it.select(USERS.PERSON_ID)
                    .from(USERS)
                    .where(USERS.FIREBASE_ID.eq(uid))
            )
        }
    }

}