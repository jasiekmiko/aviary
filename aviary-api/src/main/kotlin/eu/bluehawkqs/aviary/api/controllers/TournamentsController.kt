package eu.bluehawkqs.aviary.api.controllers

import com.google.firebase.auth.FirebaseAuth
import eu.bluehawkqs.aviary.api.models.Tournament
import java.lang.Exception
import javax.servlet.ServletContext
import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.Path
import javax.ws.rs.core.Context

@Path("tournaments")
class TournamentsController(@Context context: ServletContext) : AviaryController(context) {
    private val tournamentsDao = di.tournamentsDao()
    private val usersDao = di.usersDao()

    @GET
    fun getAllTournaments(@HeaderParam("Authorization") authHeader: String): List<Tournament> {
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
            val id = usersDao.getByFirebaseID(completedTask.result.uid)
            return tournamentsDao.getAll(id)
        } else {
            throw Exception("Authentication failed", completedTask.exception)
        }
    }

}

