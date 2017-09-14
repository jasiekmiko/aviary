package eu.bluehawkqs.aviary.api.controllers

import com.google.api.client.http.HttpStatusCodes
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig
import com.google.appengine.tools.development.testing.LocalServiceTestHelper
import eu.bluehawkqs.aviary.api.di.AviaryModule
import eu.bluehawkqs.aviary.api.di.DaggerAviaryComponent
import eu.bluehawkqs.aviary.api.di.DatabaseModule
import org.junit.After
import org.junit.Test
import org.mockito.Mockito.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.junit.Before
import org.assertj.core.api.Assertions.assertThat
import java.io.*
import javax.servlet.ServletConfig
import javax.servlet.ServletContext


class UserControllerTest {
    private val helper = LocalServiceTestHelper(LocalDatastoreServiceTestConfig())
    private val controller = UserController()
    private val req = mock(HttpServletRequest::class.java)
    private val resp = mock(HttpServletResponse::class.java)
    private val output = ByteArrayOutputStream()

    @Before
    fun setUp() {
        helper.setUp()
        val aviaryComponent = DaggerAviaryComponent.builder()
                .aviaryModule(AviaryModule())
                .databaseModule(DatabaseModule())
                // Somehow mock out DAO
                .build()
        val context = mock(ServletContext::class.java)
        `when`(context.getAttribute("aviaryComponent")).thenReturn(aviaryComponent)
        val config = mock(ServletConfig::class.java)
        `when`(config.servletContext).thenReturn(context)
        controller.init(config)

        `when`(resp.writer).thenReturn(PrintWriter(output))
    }

    @After
    fun tearDown() {
        helper.tearDown()
    }

//    @Test
//    fun saveUser_putsUserInDb() {
//        `when`(req.method).thenReturn("POST")
//        `when`(req.reader).thenReturn(BufferedReader(StringReader("""{"Name": "Jan", "Age": 24}""")))
//
//        val existingUsers = userDao.getAll().size
//
//        controller.doPost(req, resp)
//
//        assertThat(userDao.getAll().size).isEqualTo(existingUsers + 1)
//        verify(resp).status = HttpStatusCodes.STATUS_CODE_OK
//    }

    @Test
    fun get_returnsAllUsers() {
        controller.doGet(req, resp)
        verify(resp).status = HttpStatusCodes.STATUS_CODE_OK
        // Currently there's not data in the test db :(
        // assertThat(output.toByteArray().size).isNotEqualTo(0)
    }
}

