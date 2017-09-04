package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.di.AviaryComponent
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "Status", value = "/status")
class StatusController : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        val aviaryComponent = this.servletContext.getAttribute("aviaryComponent") as AviaryComponent
        res.writer.write("Status: OK\n")
        res.writer.write("Author: ${aviaryComponent.authorName()}\n")
    }
}
