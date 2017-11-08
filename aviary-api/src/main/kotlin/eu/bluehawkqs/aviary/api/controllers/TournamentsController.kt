package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.models.Tournament
import javax.servlet.ServletContext
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Context

@Path("tournaments")
class TournamentsController(@Context context: ServletContext) : AviaryController2(context) {
    private val tournamentsDao = aviaryComponent.tournamentsDao()

    @GET
    fun doGet(): List<Tournament> {
        return tournamentsDao.getAll()
    }

}

