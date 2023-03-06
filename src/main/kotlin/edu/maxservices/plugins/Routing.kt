package edu.maxservices.plugins

import edu.maxservices.connectDB
import edu.maxservices.models.AdminManager
import edu.maxservices.models.CourseManager
import edu.maxservices.models.StudentManager
import edu.maxservices.models.UniversityManager
import edu.maxservices.routes.*

import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    val dbConn = connectDB()
    val studentManager = StudentManager(dbConn)
    val universityManager = UniversityManager(dbConn)
    val courseManager = CourseManager(dbConn)
    val adminManager = AdminManager(dbConn)
    routing {
        studentApi(studentManager)
        universityApi(universityManager)
        courseApi(courseManager)
        adminApi(adminManager)
    }
}
