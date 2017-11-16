package eu.bluehawkqs.aviary.api.dao

import eu.bluehawkqs.aviary.api.dao.aviary.Tables.TOURNAMENTS
import eu.bluehawkqs.aviary.api.models.Tournament
import javax.inject.Inject

class TournamentsDao @Inject constructor(private val db: DbConnectionManager) {
    fun getAll(): List<Tournament> {
        return db.run {
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
                        it[TOURNAMENTS.SIZE]
                )
            }
        }
    }
}