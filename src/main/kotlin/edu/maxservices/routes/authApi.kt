package edu.maxservices.routes

import edu.maxservices.apiV
import edu.maxservices.models.Auth
import edu.maxservices.models.AuthManager
import edu.maxservices.models.Student
import edu.maxservices.models.StudentManager
import edu.maxservices.plugins.Helpers
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authApi(authManager: AuthManager) {
    route("/api") {
        route("/v$apiV") {
            route("auth") {
                // Login
                patch() {
                    val auth = call.receive<Auth>()
                    try {
                        Helpers().Check().loginCheck(auth)
                        val id = authManager.login(auth)
                        call.respond(id)
                    } catch (e: Exception) {
                        println(e)
                        call.respond(HttpStatusCode.Unauthorized)
                    }
                }
            }
        }
    }
}