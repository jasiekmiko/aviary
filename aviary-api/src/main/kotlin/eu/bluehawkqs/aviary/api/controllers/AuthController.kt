package eu.bluehawkqs.aviary.api.controllers

import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import java.io.IOException
import javax.servlet.ServletConfig
import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "Auth", value = "/auth")
class AuthController : HttpServlet() {
    private var context: ServletContext? = null

    override fun init(config: ServletConfig?) {
        super.init(config)
        context = config?.servletContext

        if (context == null) {
            System.out.println("Context is null, wtf!")
            return
        }
        val firebaseCredentials = context!!.getResourceAsStream("/WEB-INF/Aviary-firebase-credentials.json")
        val options = FirebaseOptions.Builder()
                .setServiceAccount(firebaseCredentials)
                .build()
        FirebaseApp.initializeApp(options)
    }

    override fun doOptions(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.addHeader("Access-Control-Allow-Origin", "*")
        resp.addHeader("Access-Control-Allow-Headers", "Authorization")
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType = "text/html"
        resp.addHeader("Access-Control-Allow-Origin", "*")

        val token = req.getHeader("Authorization")
        if (token == null) {
            resp.writer.write("Empty auth token received")
        } else {
            val completedTask = {
                val task = FirebaseAuth
                        .getInstance()
                        .verifyIdToken(token)
                while (!task.isComplete) {
                    Thread.sleep(1000)
                }
                task
            }()
            if (completedTask.isSuccessful) {
                resp.writer.write("User ID:" + completedTask.result.uid)
            } else {
                resp.writer.write("Authentication failed: " + completedTask.exception)
            }
        }
    }
}
