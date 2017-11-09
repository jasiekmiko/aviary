package eu.bluehawkqs.aviary.api.controllers

import eu.bluehawkqs.aviary.api.models.AviaryUser
import javax.servlet.ServletContext
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context

@Path("users")
class UsersController(@Context context: ServletContext) : AviaryController(context) {
    private val usersDao = di.usersDao()

    @GET
    fun doGet(): List<AviaryUser> {
        return usersDao.getAll()
    }

    @POST
    fun doPost(newUser: AviaryUser) {
        usersDao.addUser(newUser)
    }

}