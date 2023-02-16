package edu.maxservices.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.aRoute() {
    route ("/a") {
        get() {
            call.respondText("Bye World!")
        }
    }
}