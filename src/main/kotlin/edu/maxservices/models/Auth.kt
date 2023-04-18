package edu.maxservices.models

import kotlinx.serialization.Serializable
import java.sql.Connection

@Serializable
class Auth (
    val login: String,
    val password: String,
    val name: String,
    val role: Int, // 1 - Student, 2 - University, 3 - Admin
) {}

class AuthManager(private val conn : Connection) {
    private val loginStudent = "SELECT id FROM students WHERE login = ? AND password = ?"
    private val loginUniversity = "SELECT id FROM universities WHERE login = ? AND password = ?"

    fun login(auth: Auth) : Int {
        var id: Int = 0;
        when (auth.role) {
            1 -> {
                // Student
                val statement = conn.prepareStatement(loginStudent)
                statement.setString(1, auth.login)
                statement.setString(2, auth.password)
                statement.execute()
                val resSet = statement.resultSet ?: throw Exception("Student not found")
                resSet.next()
                id = resSet.getInt("id")
                return id
            }
            2 -> {
                // University
            }
            3 -> {
                // Admin
            }
            else -> {
                // Handle exception
            }
        }
        return id
    }
}

