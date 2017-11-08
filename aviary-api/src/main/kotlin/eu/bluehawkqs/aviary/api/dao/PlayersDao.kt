package eu.bluehawkqs.aviary.api.dao

import eu.bluehawkqs.aviary.api.dao.aviary.Tables.*
import eu.bluehawkqs.aviary.api.dao.aviary.tables.TournamentAttendees
import eu.bluehawkqs.aviary.api.dao.aviary.tables.records.TournamentAttendeesRecord
import eu.bluehawkqs.aviary.api.models.Person
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import javax.inject.Inject
import javax.sql.DataSource

class PlayersDao @Inject constructor(private val ds: DataSource) {
    fun getAllByTournament(tournamentId: Int): List<Person> {
        ds.connection.use { conn ->
            val create = DSL.using(conn, SQLDialect.MYSQL)
            return create
                    .select(PERSONS.ID,
                            PERSONS.FIRST_NAME,
                            PERSONS.LAST_NAME,
                            PERSONS.GENDER,
                            PERSONS.DOB)
                    .from(PERSONS)
                    .join(TOURNAMENT_ATTENDEES).on(PERSONS.ID.eq(TOURNAMENT_ATTENDEES.ATTENDEE))
                    .where(TOURNAMENT_ATTENDEES.TOURNAMENT.eq(tournamentId))
                    // TODO use custom types to simplify this to .fetchInto(Person::class.java) https://www.jooq.org/doc/3.9/manual/code-generation/custom-data-types/
                    .fetch()
                    .map {
                        Person(
                                it[PERSONS.ID],
                                it[PERSONS.FIRST_NAME],
                                it[PERSONS.LAST_NAME],
                                it[PERSONS.DOB].toLocalDateTime().toLocalDate(),
                                it[PERSONS.GENDER]
                        )
                    }
        }
    }

    fun addPlayerToTournament(personId: Int, tournamentId: Int) {
        ds.connection.use { conn ->
            val create = DSL.using(conn, SQLDialect.MYSQL)
            val tournamentPlayer = TournamentAttendeesRecord(personId, tournamentId)
            create.newRecord(TOURNAMENT_ATTENDEES, tournamentPlayer).store()
        }
    }
}