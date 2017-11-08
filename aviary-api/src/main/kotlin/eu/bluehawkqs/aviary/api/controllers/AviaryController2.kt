package eu.bluehawkqs.aviary.api.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import eu.bluehawkqs.aviary.api.di.AviaryComponent
import javax.servlet.ServletContext

abstract class AviaryController2(context: ServletContext) {
    val aviaryComponent = context.getAttribute("aviaryComponent") as AviaryComponent
    val mapper: ObjectMapper = aviaryComponent.getJsonMapper()

//    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
//        resp.contentType = "application/json"
//        // TODO Investigate setting up a proxy in dev angular to remove all cross-origin stuff. either way tighten this up
//        resp.addHeader("Access-Control-Allow-Origin", "*")
//        resp.addHeader("Access-Control-Allow-Headers", "Authorization")
//        resp.addHeader("Access-Control-Allow-Headers", "Content-Type")
//        super.service(req, resp)
//    }

}