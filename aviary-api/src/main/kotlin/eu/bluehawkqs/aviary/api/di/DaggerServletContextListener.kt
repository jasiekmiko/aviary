package eu.bluehawkqs.aviary.api.di

import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener


class DaggerServletContextListener : ServletContextListener {

    override fun contextInitialized(sce: ServletContextEvent) {
        val aviaryComponent = DaggerAviaryComponent.builder()
                .aviaryModule(AviaryModule())
                .databaseModule(DatabaseModule())
                .build()
        sce.servletContext.setAttribute("aviaryComponent", aviaryComponent)
    }

    override fun contextDestroyed(sce: ServletContextEvent) {}

}

