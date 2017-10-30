package eu.bluehawkqs.aviary.api.controllers

import com.google.api.client.http.HttpStatusCodes
import eu.bluehawkqs.aviary.api.dao.PlayersDao
import eu.bluehawkqs.aviary.api.di.AviaryComponent
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// TODO set up tournament id parameter "/api/tournaments/:id"
@WebServlet(name = "Tournament", value = "/api/tournament")
class TournamentController : AviaryController() {
    override fun depInjInit(aviaryComponent: AviaryComponent) {
        playersDao = aviaryComponent.playersDao()
    }

    private lateinit var playersDao: PlayersDao

    public override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.status = HttpStatusCodes.STATUS_CODE_OK
        mapper.writeValue(resp.writer, playersDao.getAllByTournament(1))
    }

}