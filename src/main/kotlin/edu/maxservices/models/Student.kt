package edu.maxservices.models

import edu.maxservices.plugins.Helpers
import edu.maxservices.plugins.Helpers.*
import kotlinx.serialization.Serializable
import java.sql.Connection
import java.sql.ResultSet

@Serializable
class Student (
    private val id: Int,
    private val name: String,
    private val login: String,
    private val password: String,
    private val applies: MutableList<Int>,
    private val scores: HashMap<Exams, Int>,
) : User(id, name, login, password) {

    fun getScore(req: List<Exams>): Int {
        var res = 0
        for (i in req) {
            res += if (scores[i] != null) scores[i]!! else 0
        }
        return res
    }
    fun getLinkApplies(): MutableList<Int> {
        return applies
    }
    fun getApplies(): MutableList<Int> {
        return applies.toMutableList()
    }
}

class StudentManager(private val conn : Connection) {

    private val TableCreate =
        "CREATE TABLE IF NOT EXISTS students (" +
                "    id SERIAL PRIMARY KEY," +
                "    name TEXT NOT NULL DEFAULT ''," +
                "    login TEXT NOT NULL DEFAULT ''," +
                "    password TEXT NOT NULL DEFAULT ''," +
                "    applies INTEGER[] DEFAULT array[]::integer[]," +
                "    exams INTEGER[] DEFAULT array[]::integer[]" +
                ");"
    private val SelectById = "SELECT * FROM students WHERE id = ?"
    private val SelectAll = "SELECT * FROM students;"
    private val Insert = "INSERT INTO students (id, name, login, password, applies, exams)" +
            "VALUES (?, ?, ?, ?, ?, ?) RETURNING id;"
    private val Update = "UPDATE students SET name = ?, login = ?, password = ?, applies = ?, exams = ?" +
            " WHERE id = ?"
    private val DeleteById = "DELETE FROM students WHERE id = ?"
    init {
        val statement = conn.createStatement()
        statement.execute(TableCreate)
    }

    fun getAll(): List<Student> {
        val statement = conn.createStatement()
        statement.execute(SelectAll)
        val resSet = statement.resultSet
        return Helpers().Parse().resultSetToStudentsList(resSet)
    }
}
