package edu.maxservices.routes

import edu.maxservices.apiV
import edu.maxservices.models.University
import edu.maxservices.models.UniversityManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.universityApi(universityManager: UniversityManager) {
    route("/api") {
        route("/v$apiV") {
            route("universities") {
                get("{id?}") {
                    val id = call.parameters["id"]
                    if (id == null) {
                        call.respond(universityManager.getAll())
                    } else {
                        try {
                            call.respond(universityManager.getById(id.toInt()))
                        } catch (e: Exception) {
                            universityManager.err(e)
                            call.respond(HttpStatusCode.BadRequest)
                        }
                    }
                }
                post() {
                    val university = call.receive<University>()
                    call.respond(universityManager.add(university))
                }
                patch() {
                    val university = call.receive<University>()
                    try {
                        val id = universityManager.change(university)
                        call.respond(id)
                    } catch (e: Exception) {
                        universityManager.err(e)
                        call.respond(HttpStatusCode.BadRequest)
                    }
                }
                delete("{id?}") {
                    val id = call.parameters["id"]
                    if (id != null) {
                        try {
                            call.respond(universityManager.deleteById(id.toInt()))
                        } catch (e: Exception) {
                            universityManager.err(e)
                            call.respond(HttpStatusCode.BadRequest)
                        }
                    } else {
                        call.respond(HttpStatusCode.Forbidden)
                    }
                }
            }
        }
    }
}