package edu.maxservices.routes

import edu.maxservices.apiV
import io.ktor.server.routing.*

fun Route.authRoute() {
    route("/api") {
        route("/$apiV") {
            route("/auth") {
                // Registration
                post {

                }
                // Logging
                patch {

                }

            }
        }
    }
}