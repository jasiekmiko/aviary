package eu.bluehawkqs.aviary.di

import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener


class DaggerServletContextListener : ServletContextListener {

    override fun contextInitialized(sce: ServletContextEvent) {
        val aviaryComponent = DaggerAviaryComponent.create()
        sce.servletContext.setAttribute("aviaryComponent", aviaryComponent)
    }

    override fun contextDestroyed(sce: ServletContextEvent) {}

}

