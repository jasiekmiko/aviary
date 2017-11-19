package eu.bluehawkqs.aviary.api.controllers

import com.google.firebase.auth.FirebaseAuth
import eu.bluehawkqs.aviary.api.models.TournamentDetails
import java.lang.Exception
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
        // TODO implement proper async stuff
        val completedTask = {
            val task = FirebaseAuth
                .getInstance()
                .verifyIdToken(token)
            while (!task.isComplete) {
                Thread.sleep(1000)
            }
            task
        }()
        val user = if (completedTask.isSuccessful) {
            usersDao.getByFirebaseID(completedTask.result.uid)
        } else {
            throw Exception("Authentication failed", completedTask.exception)
        }
        return TournamentDetails(
            playersDao.getAllByTournament(tournamentId),
            tournamentsDao.get(tournamentId, user))
    }

    @POST
    fun doPost(@PathParam("tournamentId") tournamentId: Int, playerId: Int? = null, @HeaderParam("Authorization") authHeader: String) {
        val id = if (playerId != null) {
            playerId
        } else {
            // TODO commonize
            val token = authHeader.substringAfter("Bearer ")
            // TODO implement proper async stuff
            val completedTask = {
                val task = FirebaseAuth
                    .getInstance()
                    .verifyIdToken(token)
                while (!task.isComplete) {
                    Thread.sleep(1000)
                }
                task
            }()
            if (completedTask.isSuccessful) {
                usersDao.getByFirebaseID(completedTask.result.uid)
            } else {
                throw Exception("Authentication failed", completedTask.exception)
            }
        }
        playersDao.addPlayerToTournament(id, tournamentId)
    }

}