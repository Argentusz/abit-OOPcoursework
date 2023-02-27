package edu.maxservices.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import edu.maxservices.models.*

fun Route.exampleRoute2() {
    route("/example2") {
        get() {
            val course = Course(
                0, "IVT", "", 200,
                2, 2,
                listOf(Exams.Russian, Exams.IT, Exams.Math),
                mutableListOf()
            )
            val student0 = Student(
                    0, "Maksym", "login", "password",
                    mutableListOf(),
                    hashMapOf(
                        Exams.Math to 84,
                        Exams.Russian to 90,
                        Exams.IT to 90,
                        Exams.Physics to 90,
                    )
            )
            course.newApplicant(student0)
            val student1 = Student(
                1, "Elizaveta", "login1", "password1",
                mutableListOf(),
                hashMapOf(
                    Exams.Math to 95,
                    Exams.Russian to 98,
                    Exams.IT to 100,
                )
            )
            course.newApplicant(student1)
            val student2 = Student(
                2, "Mikhail", "mishhgun01", ":(",
                mutableListOf(),
                hashMapOf(
                    Exams.Math to 80,
                    Exams.Russian to 82,
                    Exams.IT to 88,
                )
            )
            course.newApplicant(student2)
            val student3 = Student(
                3, "Gleb", "Garlic", "withToast",
                mutableListOf(),
                hashMapOf(
                    Exams.Russian to 100,
                    Exams.Math to 100,
                    Exams.IT to 100,
                    Exams.Physics to 100,
                    Exams.Ingirmanlandian to 100,
                    Exams.Literature to 100,
                )
            )
            course.newApplicant(student3)
            val student4 = Student(
                4, "Dummy", "I'm dumb", "I'm dumb",
                mutableListOf(),
                hashMapOf(
                    Exams.IT to 50,
                    Exams.Math to 50,
                    Exams.Russian to 50,
                    Exams.Ingirmanlandian to 100,
                    Exams.Literature to 100,
                    Exams.English to 100,
                    Exams.Physics to 100,
                )
            )
            course.newApplicant(student4)

            var text = "IVT - 2 Budget places, 2 Commerce places\nApplicants:\n"
            val apls : MutableList<Pair<Student, Int>> = mutableListOf()
            val applicants = course.getApplicants()
            for (i in applicants.indices) {
                text += applicants[i].name() + " "+ applicants[i].getScore(course.getExams()).toString() + "\n"
            }
            text += "\nSorted:\n"
            var results = course.sortApplicants()

            for (i in results) {
                text += i.first.name() + " " + i.first.getApplies()[0] + " "+ i.second.toString() + "\n"
            }




            results = course.budgetApplicants()
            text += "\nBudget:\n"
            for (i in results) {
                text += i.first.name() + " "  + i.second.toString() + "\n"
            }
            results = course.commerceApplicants()
            text += "\nCommerce:\n"
            for (i in results) {
                text += i.first.name() + " " + i.second.toString() + "\n"
            }
            call.respond(student0)
        }
    }
}