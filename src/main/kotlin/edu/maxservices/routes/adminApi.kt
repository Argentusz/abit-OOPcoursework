package edu.maxservices.routes

import edu.maxservices.apiV
import edu.maxservices.models.Admin
import edu.maxservices.models.AdminManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.adminApi(adminManager: AdminManager) {
    route("/api") {
        route("/v$apiV") {
            route("admins") {
                get("{id?}") {
                    val id = call.parameters["id"]
                    if (id == null) {
                        call.respond(adminManager.getAll())
                    } else {
                        try {
                            call.respond(adminManager.getById(id.toInt()))
                        } catch (e: Exception) {
                            adminManager.err(e)
                            call.respond(HttpStatusCode.BadRequest)
                        }
                    }
                }
                post() {
                    val admin = call.receive<Admin>()
                    call.respond(adminManager.add(admin))
                }
                patch() {
                    val admin = call.receive<Admin>()
                    call.respond(adminManager.change(admin))
                }
                delete("{id?}") {
                    val id = call.parameters["id"]
                    if (id != null) {
                        try {
                            call.respond(adminManager.deleteById(id.toInt()))
                        } catch (e: Exception) {
                            adminManager.err(e)
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