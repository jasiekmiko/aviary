package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.authentication.FirebaseAuth
import eu.bluehawkqs.aviary.api.models.TournamentDetails
import javax.servlet.ServletContext
import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
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
    fun doGet(@PathParam("tournamentId") tournamentId: Int, @HeaderParam("Authorization") authHeader: String): TournamentDetails {
        val token = authHeader.substringAfter("Bearer ")
        val uid = FirebaseAuth.verifyIdToken(token).uid
        val user = usersDao.getUserIdByFirebaseID(uid)
        return TournamentDetails(
            playersDao.getAllByTournament(tournamentId),
            tournamentsDao.get(tournamentId, user))
    }

    @POST
    fun doPost(@PathParam("tournamentId") tournamentId: Int, playerId: Int? = null, @HeaderParam("Authorization") authHeader: String) {
        val id = if (playerId != null) {
            playerId
        } else {
            val token = authHeader.substringAfter("Bearer ")
            val uid = FirebaseAuth.verifyIdToken(token).uid
            usersDao.getUserIdByFirebaseID(uid)
        }
        playersDao.addPlayerToTournament(id, tournamentId)
    }

}