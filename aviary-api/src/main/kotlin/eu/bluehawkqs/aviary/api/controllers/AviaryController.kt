package eu.bluehawkqs.aviary.api.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import eu.bluehawkqs.aviary.api.di.AviaryComponent
import javax.servlet.ServletConfig
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

abstract class AviaryController : HttpServlet() {
    protected lateinit var mapper: ObjectMapper

    open fun depInjInit(aviaryComponent: AviaryComponent) {}

    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType = "text/html" //TODO change to JSON
        resp.addHeader("Access-Control-Allow-Origin", "*")
        resp.addHeader("Access-Control-Allow-Headers", "Authorization")
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type")
        super.service(req, resp)
    }

    override fun init(config: ServletConfig?) {
        super.init(config)
        val aviaryComponent = this.servletContext.getAttribute("aviaryComponent") as AviaryComponent
        mapper = aviaryComponent.getJsonMapper()
        depInjInit(aviaryComponent)
    }
}