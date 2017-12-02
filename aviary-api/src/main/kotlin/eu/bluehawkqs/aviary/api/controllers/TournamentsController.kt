package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.models.Tournament
import eu.bluehawkqs.aviary.api.services.AppengineFirebaseAuth
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
        val token = authHeader.substringAfter("Bearer ")
        val uid = AppengineFirebaseAuth.verifyIdToken(token).uid
        val currentUserId = usersDao.getUserIdByFirebaseID(uid)
        return tournamentsDao.getAll(currentUserId)
    }

}

