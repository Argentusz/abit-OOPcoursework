package edu.maxservices.models

import edu.maxservices.plugins.LogsManager
import kotlinx.serialization.Serializable
import java.sql.Connection
import java.util.logging.Logger

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
    private val logger = LogsManager(this.javaClass.name)
    fun log(str: String) { this.logger.log(str) }
    fun err(str: String) {this.logger.err(str)}
    fun err(e: Exception) {this.logger.err(e.toString())}

    init {

    }
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
                val statement = conn.prepareStatement(loginUniversity)
                statement.setString(1, auth.login)
                statement.setString(2, auth.password)
                statement.execute()
                val resSet = statement.resultSet ?: throw Exception("University not found")
                resSet.next()
                id = resSet.getInt("id")
                return id
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

