package eu.bluehawkqs.aviary.api.controllers

import com.google.api.client.http.HttpStatusCodes
import eu.bluehawkqs.aviary.api.di.AviaryComponent
import javax.servlet.ServletConfig
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "Users", value = "/users")
class UserController : HttpServlet() {
    private lateinit var aviaryComponent: AviaryComponent

    public override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.status = HttpStatusCodes.STATUS_CODE_OK
        aviaryComponent.userDao().getAll().forEach { resp.writer.write("""${it.firstName} ${it.lastName}""") }
    }

    override fun init(config: ServletConfig?) {
        super.init(config)
        aviaryComponent = this.servletContext.getAttribute("aviaryComponent") as AviaryComponent
    }
}