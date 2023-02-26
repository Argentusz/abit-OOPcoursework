package edu.maxservices.routes

import edu.maxservices.apiV
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import edu.maxservices.models.*

fun Route.exampleRoute() {
    route ("/example") {
        route ("/v$apiV") {
            get() {
                val reqExams = listOf(Exams.Russian, Exams.IT, Exams.Math)
                val course = Course(0, "IVT", "", 200, 2, 2, reqExams, mutableListOf())
                val applies = mutableListOf(course.getId())
                val scores = hashMapOf<Exams, Int>()
                scores[Exams.Russian] = 100
                scores[Exams.IT] = 90
                scores[Exams.Math] = 100
                scores[Exams.Physics] = 228
                val s = Student(0, "Student", "student", "qwerty", applies, scores)
                val nowy = course.getExams().toMutableList()
                nowy[0] = Exams.Physics
                call.respondText(
                    s.getScore(course.getExams()).toString() + course.getExams().toString() + nowy.toString()
                )
            }
        }
    }
}