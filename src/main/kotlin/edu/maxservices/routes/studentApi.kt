package edu.maxservices.routes

import edu.maxservices.apiV
import edu.maxservices.models.Auth
import edu.maxservices.models.Student
import edu.maxservices.models.StudentManager
import edu.maxservices.plugins.Helpers
import edu.maxservices.plugins.LogsManager
import edu.maxservices.plugins.StudentReceiver
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
                                studentManager.err(e)
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
                                studentManager.err(e)
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
                                studentManager.err(e)
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
                            studentManager.err(e)
                            call.respond(HttpStatusCode.BadRequest)
                        }
                    }
                }
                post() {
                    val student = call.receive<Student>()
                    if (!studentManager.checkUniqueLogin(student.login)) {
                        call.respond(HttpStatusCode.Unauthorized)
                    }
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
                        studentManager.err("Registration check of login = ${student.login} password = ${student.password} not passed")
                        call.respond(HttpStatusCode.BadRequest)
                    }
                }
                delete("{id?}") {
                    val id = call.parameters["id"]
                    if (id != null) {
                        try {
                            call.respond(studentManager.deleteById(id.toInt()))
                        } catch (e: Exception) {
                            studentManager.err(e)
                            call.respond(HttpStatusCode.BadRequest)
                        }
                    } else {
                        call.respond(HttpStatusCode.Forbidden)
                    }
                }
                route("/check") {
                    get("{login}") {
                        val login = call.parameters["login"]
                        if (login === null) {
                            call.respond(HttpStatusCode.BadRequest)
                        }
                        try {
                            val res = studentManager.checkUniqueLogin(login!!)
                            if (res) {
                                call.respond(HttpStatusCode.OK)
                            }
                            call.respond(HttpStatusCode.Unauthorized)
                        } catch (e: Exception) {
                            studentManager.err(e)
                            call.respond(HttpStatusCode.BadRequest)
                        }
                    }
                }
            }
        }
    }
}