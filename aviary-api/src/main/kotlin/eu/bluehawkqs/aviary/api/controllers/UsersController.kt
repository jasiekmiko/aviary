package eu.bluehawkqs.aviary.api.controllers

import com.fasterxml.jackson.module.kotlin.readValue
import com.google.api.client.http.HttpStatusCodes
import eu.bluehawkqs.aviary.api.dao.UsersDao
import eu.bluehawkqs.aviary.api.di.AviaryComponent
import eu.bluehawkqs.aviary.api.models.AviaryUser
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "Users", value = "/api/users")
class UsersController : AviaryController() {
    override fun depInjInit(aviaryComponent: AviaryComponent) {
        usersDao = aviaryComponent.usersDao()
    }

    private lateinit var usersDao: UsersDao

    public override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.status = HttpStatusCodes.STATUS_CODE_OK
        mapper.writeValue(resp.writer, usersDao.getAll())
    }

    public override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val newUser = mapper.readValue<AviaryUser>(req.reader)
        usersDao.addUser(newUser)
        resp.status = HttpStatusCodes.STATUS_CODE_OK
    }

}