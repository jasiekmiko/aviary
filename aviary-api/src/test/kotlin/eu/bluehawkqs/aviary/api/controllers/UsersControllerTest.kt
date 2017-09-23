package eu.bluehawkqs.aviary.api.controllers

import com.google.api.client.http.HttpStatusCodes
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig
import com.google.appengine.tools.development.testing.LocalServiceTestHelper
import eu.bluehawkqs.aviary.api.dao.AviaryUser
import eu.bluehawkqs.aviary.api.dao.Person
import eu.bluehawkqs.aviary.api.dao.UsersDao
import eu.bluehawkqs.aviary.api.di.AviaryComponent
import org.junit.After
import org.junit.Test
import org.mockito.Mockito.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.junit.Before
import org.assertj.core.api.Assertions.assertThat
import java.io.*
import java.time.LocalDate
import javax.servlet.ServletConfig
import javax.servlet.ServletContext


class UsersControllerTest {
    private val helper = LocalServiceTestHelper(LocalDatastoreServiceTestConfig())
    private val controller = UsersController()
    private val req = mock(HttpServletRequest::class.java)
    private val resp = mock(HttpServletResponse::class.java)
    private val output = ByteArrayOutputStream()
    private val printWriter : PrintWriter = PrintWriter(output, true)

    private val mockUserDao = mock(UsersDao::class.java)

    @Before
    fun setUp() {
        helper.setUp()
        val aviaryComponent = mock(AviaryComponent::class.java)
        val context = mock(ServletContext::class.java)
        `when`(context.getAttribute("aviaryComponent")).thenReturn(aviaryComponent)
        val config = mock(ServletConfig::class.java)
        `when`(config.servletContext).thenReturn(context)
        controller.init(config)

        `when`(aviaryComponent.usersDao()).thenReturn(mockUserDao)

        `when`(resp.writer).thenReturn(printWriter)
    }

    @After
    fun tearDown() {
        helper.tearDown()
    }

    @Test
    fun saveUser_putsUserInDb() {
        `when`(req.method).thenReturn("POST")
        `when`(req.reader).thenReturn(BufferedReader(StringReader("""{"firstName": "Rob", "lastName": "Stark"}""")))

        controller.doPost(req, resp)

    }

    @Test
    fun get_returnsAllUsers() {
        `when`(mockUserDao.getAll()).thenReturn(listOf(AviaryUser("aNewFirebaseId",
                Person(0, "Daenerys", "Targaryen", LocalDate.now(), "dragon"))))

        controller.doGet(req, resp)

        verify(resp).status = HttpStatusCodes.STATUS_CODE_OK
        printWriter.flush()
        assertThat(output.toString()).contains("Daenerys")
        assertThat(output.toString()).contains("Targaryen")
    }
}
