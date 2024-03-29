package edu.maxservices

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import edu.maxservices.plugins.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import java.sql.Connection
import java.sql.DriverManager
import io.ktor.http.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.httpsredirect.*

const val apiV = "1"
fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::module)
        .start(wait = true)
}


fun Application.module() {
    configureRouting()
    configureSerialization()
    configureHTTP()
}



fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
fun connectDB(): Connection {
    Class.forName("org.postgresql.Driver")
    val url = "jdbc:postgresql://localhost:2228/postgres"
    return DriverManager.getConnection(url, "mt", "")
}

fun Application.configureHTTP() {
    install(CORS) {
        headersOf("Access-Control-Allow-Origin", "GET, POST, PATCH, PUT, DELETE, OPTIONS")
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.ContentType)
        anyHost()
    }
}