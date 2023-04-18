package edu.maxservices.routes

import edu.maxservices.apiV
import edu.maxservices.models.Student
import edu.maxservices.models.StudentManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.studentApi(studentManager: StudentManager) {
    route("/api") {
        route("/v$apiV") {
            route("students") {

                route("/to_courses") {
                    get("{id?}") {
                        val id = call.parameters["id"]
                        if (id == null) {
                            call.respond(HttpStatusCode.Forbidden)
                        } else {
                            try {
                                call.respond(studentManager.findApplies(id.toInt()))

                            } catch(e: Exception) {
                                println(e)
                                call.respond(HttpStatusCode.BadRequest)
                            }
                        }
                    }
                }

                get("{id?}") {
                    val id = call.parameters["id"]
                    if (id == null) {
                        call.respond(studentManager.getAll())
                    } else {
                        try {
                            call.respond(studentManager.getById(id.toInt()))
                        } catch (e: Exception) {
                            println(e)
                            call.respond(HttpStatusCode.BadRequest)
                        }
                    }
                }
                post() {
                    val student = call.receive<Student>()
                    call.respond(studentManager.add(student))
                }
                patch() {
                    val student = call.receive<Student>()
                    call.respond(studentManager.change(student))
                }
                delete("{id?}") {
                    val id = call.parameters["id"]
                    if (id != null) {
                        try {
                            call.respond(studentManager.deleteById(id.toInt()))
                        } catch (e: Exception) {
                            println(e)
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