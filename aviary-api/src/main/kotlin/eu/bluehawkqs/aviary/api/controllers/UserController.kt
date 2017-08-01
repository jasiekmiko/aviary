package eu.bluehawkqs.aviary.api.controllers

import com.google.api.client.http.HttpStatusCodes
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UserController : HttpServlet() {
    public override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.status = HttpStatusCodes.STATUS_CODE_OK
    }
}