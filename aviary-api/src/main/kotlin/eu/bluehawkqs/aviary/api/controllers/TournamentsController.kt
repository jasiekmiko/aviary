package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.authentication.Secured
import eu.bluehawkqs.aviary.api.models.Tournament
import javax.servlet.ServletContext
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Context

@Path("tournaments")
class TournamentsController(@Context context: ServletContext) : AviaryController(context) {
    private val tournamentsDao = di.tournamentsDao()
    private val usersDao = di.usersDao()

    @GET
    @Secured
    fun getAllTournaments(): List<Tournament> {
        // TODO Allow non-authorized users access to basic info
        val currentUserId = usersDao.getUserIdByFirebaseID(currentUserId!!)
        return tournamentsDao.getAll(currentUserId)
    }

}

