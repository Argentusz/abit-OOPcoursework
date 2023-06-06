package edu.maxservices.plugins

import edu.maxservices.connectDB
import edu.maxservices.models.*
import edu.maxservices.routes.*

import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    val dbConn = connectDB()
    val studentManager = StudentManager(dbConn)
    val universityManager = UniversityManager(dbConn)
    val courseManager = CourseManager(dbConn)
    val authManager = AuthManager(dbConn)
    routing {
        studentApi(studentManager)
        universityApi(universityManager)
        courseApi(courseManager)
        authApi(authManager)
    }
}
