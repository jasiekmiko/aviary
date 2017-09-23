package eu.bluehawkqs.aviary.api.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import eu.bluehawkqs.aviary.api.di.AviaryComponent
import javax.servlet.ServletConfig
import javax.servlet.http.HttpServlet

abstract class AviaryController : HttpServlet() {
    protected lateinit var mapper: ObjectMapper

    open fun depInjInit(aviaryComponent: AviaryComponent) {}

    override fun init(config: ServletConfig?) {
        super.init(config)
        val aviaryComponent = this.servletContext.getAttribute("aviaryComponent") as AviaryComponent
        mapper = aviaryComponent.getJsonMapper()
        depInjInit(aviaryComponent)
    }
}