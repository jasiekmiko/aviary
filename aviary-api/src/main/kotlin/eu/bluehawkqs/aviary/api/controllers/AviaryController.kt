package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.di.AviaryComponent
import javax.servlet.ServletConfig
import javax.servlet.http.HttpServlet

abstract class AviaryController : HttpServlet() {
    abstract fun depInjInit(aviaryComponent: AviaryComponent)

    override fun init(config: ServletConfig?) {
        super.init(config)
        val aviaryComponent = this.servletContext.getAttribute("aviaryComponent") as AviaryComponent
        depInjInit(aviaryComponent)
    }
}