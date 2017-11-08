package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.di.AviaryComponent
import javax.servlet.ServletContext

abstract class AviaryController(context: ServletContext) {
    val di = context.getAttribute("aviaryComponent") as AviaryComponent
}