package edu.maxservices.models

import edu.maxservices.plugins.Helpers
import kotlinx.serialization.Serializable
import java.sql.Connection

@Serializable
data class University (
    override val id: Int,
    override val name: String,
    override val login: String,
    override val password: String,
    private val courses: List<Course>
) : User() {
    fun getCourses(): List<Course> {
        return courses
    }
}

class UniversityManager(private val conn : Connection) {
    private val TableCreate =
        "CREATE TABLE IF NOT EXISTS universities (" +
                "    id SERIAL PRIMARY KEY," +
                "    name TEXT NOT NULL DEFAULT ''," +
                "    login TEXT NOT NULL DEFAULT ''," +
                "    password TEXT NOT NULL DEFAULT ''," +
                "    coursesIds INTEGER[] DEFAULT array[]::integer[]" +
                ");"
    private val SelectById = "SELECT * FROM universities WHERE id = ?"
    private val SelectAll = "SELECT * FROM universities;"
    private val Insert = "INSERT INTO universities (name, login, password, coursesIds)" +
            "VALUES (?, ?, ?, ?) RETURNING id;"
    private val Update = "UPDATE universities SET name = ?, login = ?, password = ?, coursesIds = ?" +
            " WHERE id = ? RETURNING id"
    private val DeleteById = "DELETE FROM universities WHERE id = ? RETURNING id"

    init {
        createTable()
    }

    fun createTable() {
        val statement = conn.createStatement()
        statement.execute(TableCreate)
    }

    fun getAll(): List<University> {
        val statement = conn.createStatement()
        statement.execute(SelectAll)
        val resSet = statement.resultSet
        return Helpers().Parse().resultSetToUniversityList(resSet, conn)
    }

    fun getById(id: Int) : University {
        val statement = conn.prepareStatement(SelectById)
        statement.setInt(1, id)
        statement.execute()
        val resSet = statement.resultSet
        val res = Helpers().Parse().resultSetToUniversity(resSet, conn)
        if (res != null) return res
        else throw Exception("(StudentManager.getById) No student with id = $id found.")
    }

    fun add(university: University) : Int {
        val statement = conn.prepareStatement(Insert)
        statement.setString(1, university.name())
        statement.setString(2, university.login())
        statement.setString(3, university.password())
        statement.setArray(4, Helpers().Convert().courseListToIntArraySQL(university.getCourses(), conn))
        statement.execute()
        val res = statement.resultSet
        return if (res.next()) {
            res.getInt("id")
        } else {
            -1
        }
    }

    fun change(university: University) : Int {
        val statement = conn.prepareStatement(Update)
        statement.setString(1, university.name())
        statement.setString(2, university.login())
        statement.setString(3, university.password())
        statement.setArray(4, Helpers().Convert().courseListToIntArraySQL(university.getCourses(), conn))
        statement.setInt(5, university.id())
        statement.execute()
        val resSet = statement.resultSet
        if (resSet.next()) return resSet.getInt("id")
        else throw Exception("(UniversityManager.change) No university with id = ${university.id()} found.")

    }

    fun deleteById(id: Int) : Int {
        val statement = conn.prepareStatement(DeleteById)
        statement.setInt(1, id)
        statement.execute()
        val resSet = statement.resultSet
        if (resSet.next()) return resSet.getInt("id")
        else throw Exception("(UniversityManager.deleteById) No university with id = $id found.")
    }

}