package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.models.Person
import javax.servlet.ServletContext
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Context

@Path("tournaments/{tournamentId}/players")
class TournamentController(@Context context: ServletContext) : AviaryController(context) {
    private val playersDao = di.playersDao()

    @GET
    fun doGet(@PathParam("tournamentId") tournamentId: Int): List<Person> {
        return playersDao.getAllByTournament(tournamentId)
    }

    @POST
    fun doPost(tournamentPlayerIds: TournamentPlayerIds) {
        // TODO use tournamentId instead?
        val (tournamentId, personId) = tournamentPlayerIds
        playersDao.addPlayerToTournament(personId, tournamentId)
    }

}

data class TournamentPlayerIds(val tournamentId: Int, val personId: Int)
