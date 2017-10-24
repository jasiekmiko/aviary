package eu.bluehawkqs.aviary.api.dao

import eu.bluehawkqs.aviary.api.dao.aviary.Tables.TOURNAMENTS
import eu.bluehawkqs.aviary.api.models.Tournament
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import javax.inject.Inject
import javax.sql.DataSource

class TournamentsDao @Inject constructor(private val ds: DataSource) {
    fun getAll(): List<Tournament> {
        ds.connection.use { conn ->
            val create = DSL.using(conn, SQLDialect.MYSQL)
            return create.select().from(TOURNAMENTS).fetch().map {
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