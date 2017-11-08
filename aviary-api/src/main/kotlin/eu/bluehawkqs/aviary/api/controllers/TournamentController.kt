package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.models.Person
import javax.servlet.ServletContext
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Context

@Path("tournaments/{tournamentId}/players")
class TournamentController(@Context context: ServletContext) : AviaryController(context) {
    private val playersDao = di.playersDao()

    @GET
    fun doGet(): List<Person> {
        return playersDao.getAllByTournament(1)
    }

    @POST
    fun doPost(tournamentPlayerIds: TournamentPlayerIds) {
        val (tournamentId, personId) = tournamentPlayerIds
        playersDao.addPlayerToTournament(personId, tournamentId)
    }

}

data class TournamentPlayerIds(val tournamentId: Int, val personId: Int)
