package edu.maxservices.plugins

import edu.maxservices.connectDB
import edu.maxservices.models.StudentManager
import edu.maxservices.routes.exampleRoute
import edu.maxservices.routes.exampleRoute2

import edu.maxservices.routes.rootRoute

import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    val dbConn = connectDB()
    val studManager = StudentManager(dbConn)
    routing {
        rootRoute()
        exampleRoute()
        exampleRoute2()
    }
}
