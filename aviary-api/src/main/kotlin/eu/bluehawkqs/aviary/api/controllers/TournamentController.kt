package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.authentication.Secured
import eu.bluehawkqs.aviary.api.models.TournamentDetails
import javax.servlet.ServletContext
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Context

@Path("tournaments/{tournamentId}/players")
class TournamentController(@Context context: ServletContext) : AviaryController(context) {
    private val playersDao = di.playersDao()
    private val tournamentsDao = di.tournamentsDao()
    private val usersDao = di.usersDao()

    @GET
    @Secured
    fun doGet(@PathParam("tournamentId") tournamentId: Int): TournamentDetails {
        // TODO allow non-authorized users access to basic info
        val user = usersDao.getUserIdByFirebaseID(currentUserId!!)
        return TournamentDetails(
            playersDao.getAllByTournament(tournamentId),
            tournamentsDao.get(tournamentId, user))
    }

    @POST
    @Secured
    fun doPost(@PathParam("tournamentId") tournamentId: Int, playerId: Int? = null) {
        val id = playerId ?: usersDao.getUserIdByFirebaseID(currentUserId!!)
        playersDao.addPlayerToTournament(id, tournamentId)
    }

}