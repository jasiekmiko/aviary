package eu.bluehawkqs.aviary.api.dao

import eu.bluehawkqs.aviary.api.dao.aviary.Tables.TOURNAMENTS
import eu.bluehawkqs.aviary.api.dao.aviary.Tables.TOURNAMENT_ATTENDEES
import eu.bluehawkqs.aviary.api.models.Tournament
import javax.inject.Inject

class TournamentsDao @Inject constructor(private val db: DbConnectionManager) {
    fun getAll(userId: Int): List<Tournament> {
        return db.run {
            val attended = it.fetchValues(it
                .select(TOURNAMENT_ATTENDEES.TOURNAMENT)
                .from(TOURNAMENT_ATTENDEES)
                .where(TOURNAMENT_ATTENDEES.ATTENDEE.eq(userId))
            )

            it.selectFrom(TOURNAMENTS)
                .fetch()
                .map {
                    Tournament(
                        it[TOURNAMENTS.ID],
                        it[TOURNAMENTS.NAME],
                        it[TOURNAMENTS.DESCRIPTION],
                        it[TOURNAMENTS.LOCATION],
                        it[TOURNAMENTS.START_DATE].toLocalDateTime().toLocalDate(),
                        it[TOURNAMENTS.END_DATE].toLocalDateTime().toLocalDate(),
                        it[TOURNAMENTS.END_DATE].toLocalDateTime().toLocalDate(),
                        it[TOURNAMENTS.SIZE],
                        attended.contains(it[TOURNAMENTS.ID])
                    )
                }
        }
    }
}