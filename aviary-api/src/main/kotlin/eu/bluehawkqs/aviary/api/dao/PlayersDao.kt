package eu.bluehawkqs.aviary.api.dao

import eu.bluehawkqs.aviary.api.dao.aviary.Tables.*
import eu.bluehawkqs.aviary.api.dao.aviary.tables.records.TournamentAttendeesRecord
import eu.bluehawkqs.aviary.api.models.Person
import org.jooq.exception.DataAccessException
import javax.inject.Inject
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response

class PlayersDao @Inject constructor(private val db: DbConnectionManager) {
    fun getAllByTournament(tournamentId: Int): List<Person> {
        return db.run {
            it
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
        db.run {
            val tournamentPlayer = TournamentAttendeesRecord(personId, tournamentId)
            try {
                it.newRecord(TOURNAMENT_ATTENDEES, tournamentPlayer).store()
            } catch (e: DataAccessException) {
                throw PlayerIsAlreadyAttendingTournament("Cannot add player $personId to tournament $tournamentId", e)
            }
        }
    }
}

class PlayerIsAlreadyAttendingTournament(message: String, cause: Exception)
    : WebApplicationException(message, cause, Response.Status.CONFLICT)
