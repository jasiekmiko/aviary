package eu.bluehawkqs.aviary.api.controllers

import com.google.api.client.http.HttpStatusCodes
import eu.bluehawkqs.aviary.api.dao.AviaryUser
import eu.bluehawkqs.aviary.api.dao.Person
import eu.bluehawkqs.aviary.api.dao.UsersDao
import eu.bluehawkqs.aviary.api.di.AviaryComponent
import java.time.LocalDate
import javax.servlet.ServletConfig
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "Users", value = "/users")
class UsersController : AviaryController() {
    override fun depInjInit(aviaryComponent: AviaryComponent) {
        mUsersDao = aviaryComponent.usersDao()
    }

    private lateinit var mUsersDao: UsersDao

    public override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.status = HttpStatusCodes.STATUS_CODE_OK
        mUsersDao.getAll().forEach { resp.writer.print(it) }
    }

    public override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        // TODO get values from req
        mUsersDao.addUser(AviaryUser("aNewFirebaseId",
                Person(0, "Daenerys", "Targaryen", LocalDate.now(), "dragon")))
        resp.status = HttpStatusCodes.STATUS_CODE_OK
    }

    override fun init(config: ServletConfig?) {
        super.init(config)
        val aviaryComponent = this.servletContext.getAttribute("aviaryComponent") as AviaryComponent
        mUsersDao = aviaryComponent.usersDao()
    }
}