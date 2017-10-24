package eu.bluehawkqs.aviary.api.controllers

import com.google.api.client.http.HttpStatusCodes
import eu.bluehawkqs.aviary.api.dao.TournamentsDao
import eu.bluehawkqs.aviary.api.di.AviaryComponent
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "Tournaments", value = "/api/tournaments")
class TournamentsController : AviaryController() {
    override fun depInjInit(aviaryComponent: AviaryComponent) {
        tournamentsDao = aviaryComponent.tournamentsDao()
    }

    private lateinit var tournamentsDao: TournamentsDao

    public override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.status = HttpStatusCodes.STATUS_CODE_OK
        mapper.writeValue(resp.writer, tournamentsDao.getAll())
    }

}

