package edu.maxservices.routes

import edu.maxservices.apiV
import edu.maxservices.models.Course
import edu.maxservices.models.CourseManager
import edu.maxservices.models.UniversityManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.courseApi(courseManager: CourseManager) {
    route("/api") {
        route("/v$apiV") {
            route("courses") {
                route ("default") {
                    get() {
                        try {
                            call.respond(courseManager.getAll())
                        } catch (e: Exception) {
                            courseManager.err(e)
                            call.respond(HttpStatusCode.BadRequest)
                        }
                    }
                }
                route("to_students") {
                    get("{cid?}") {
                        val cid = call.parameters["cid"]
                        if (cid == null) {
                            call.respond(HttpStatusCode.BadRequest)
                        } else {
                            try {
                                call.respond(courseManager.findApplicants(cid.toInt()))
                            } catch (e: Exception) {
                                courseManager.err(e)
                                call.respond(HttpStatusCode.BadRequest)
                            }
                        }
                    }
                }
                get("{id?}") {
                    val id = call.parameters["id"]
                    if (id == null) {
                        call.respond(courseManager.getAllForTable())
                    } else {
                        try {
                            call.respond(courseManager.getById(id.toInt()))
                        } catch (e: Exception) {
                            courseManager.err(e)
                            call.respond(HttpStatusCode.BadRequest)
                        }
                    }
                }
                post("{uid?}") {
                    val course = call.receive<Course>()
                    val uid = call.parameters["uid"]
                    if (uid == null) {
                        call.respond(HttpStatusCode.BadRequest)
                    } else {
                        val res = courseManager.add(course, uid.toInt())
                        call.respond(res)
                    }

                }
                patch() {
                    val course = call.receive<Course>()
                    call.respond(courseManager.change(course))
                }
                delete("{id?}") {
                    val id = call.parameters["id"]
                    if (id != null) {
                        try {
                            call.respond(courseManager.deleteById(id.toInt()))
                        } catch (e: Exception) {
                            courseManager.err(e)
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