package edu.maxservices.routes

import edu.maxservices.apiV
import edu.maxservices.models.Auth
import edu.maxservices.models.Student
import edu.maxservices.models.StudentManager
import edu.maxservices.plugins.Helpers
import edu.maxservices.plugins.LogsManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.studentApi(studentManager: StudentManager) {
    val logger = LogsManager(this.javaClass.name)

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
                                logger.log("Exception students/to_courses: $e")
                                call.respond(HttpStatusCode.BadRequest)
                            }
                        }
                    }
                    post("{student?}/{course?}") {
                        val studentId = call.parameters["student"]
                        val courseId = call.parameters["course"]
                        if (studentId == null || courseId == null) {
                            call.respond(HttpStatusCode.BadRequest)
                        } else {
                            try {
                                call.respond(studentManager.newApply(studentId.toInt(), courseId.toInt()))
                            } catch(e: Exception) {
                                call.respond(HttpStatusCode.NotFound)
                            }
                        }
                    }
                    delete("{student?}/{course?}") {
                        val studentId = call.parameters["student"]
                        val courseId = call.parameters["course"]
                        if (studentId == null || courseId == null) {
                            call.respond(HttpStatusCode.BadRequest)
                        } else {
                            try {
                                call.respond(studentManager.unApply(studentId.toInt(), courseId.toInt()))
                            } catch(e: Exception) {
                                call.respond(HttpStatusCode.NotFound)
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
                    if (Helpers().Check().registrationCheck(
                        Auth(
                            login = student.login,
                            password = student.password,
                            name = "",
                            role = 1,
                        )
                    )) {
                        call.respond(studentManager.change(student))
                    } else {
                        call.respond(HttpStatusCode.BadRequest)
                    }
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