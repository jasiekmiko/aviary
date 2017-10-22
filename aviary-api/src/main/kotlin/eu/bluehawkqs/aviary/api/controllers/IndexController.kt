package eu.bluehawkqs.aviary.api.controllers

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "Index", value = "/")
class IndexController : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        res.writer.write("Hello! This is the aviary root, you probably didn't mean to come here :)\n")
    }
}