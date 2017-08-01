package eu.bluehawkqs.aviary.api.controllers

import com.google.api.client.http.HttpStatusCodes
import org.junit.Test
import org.mockito.Mockito.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse



class UserControllerTest {
    private val controller = UserController()
    private val req = mock(HttpServletRequest::class.java)
    private val resp = mock(HttpServletResponse::class.java)

    @Test
    fun saveUser_returnsOk() {
        `when`(req.method).thenReturn("POST")

        controller.doPost(req, resp)

        verify(resp).status = HttpStatusCodes.STATUS_CODE_OK
    }
}

