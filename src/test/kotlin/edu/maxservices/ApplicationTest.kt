package edu.maxservices

import edu.maxservices.models.Auth
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import edu.maxservices.plugins.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ApplicationTest {
    @Test
    fun testGetStudents() = testApplication {
        application {
            module()
        }
        client.get("/api/v$apiV/students").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
        client.get("/api/v$apiV/students/1").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
        client.get("/api/v$apiV/students/21341208413908093840138409318").apply {
            assertEquals(HttpStatusCode.BadRequest, status)
        }
    }

    @Test
    fun testLogin() = testApplication {
        application {
            module()
        }
        client.patch("/api/v$apiV/auth") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(Auth("login", "password", "", 1)))

        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            assert(bodyAsText().isNotEmpty())
        }

        client.patch("/api/v$apiV/auth") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(Auth("login12", "password21", "", 1)))

        }.apply {
            assertEquals(HttpStatusCode.Unauthorized, status)
        }
    }


//    @Test
//    fun testRoot() = testApplication {
//        application {
//            configureRouting()
//        }
//        client.get("/").apply {
//            assertEquals(HttpStatusCode.OK, status)
//            assertEquals("Hello World!", bodyAsText())
//        }
//    }
}
