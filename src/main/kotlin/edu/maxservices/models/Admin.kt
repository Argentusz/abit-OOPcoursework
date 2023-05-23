package edu.maxservices.models

import edu.maxservices.plugins.Helpers
import edu.maxservices.plugins.LogsManager
import kotlinx.serialization.Serializable
import java.sql.Connection

@Serializable
data class Admin (
    override val id: Int,
    override val name: String,
    override val login: String,
    override val password: String,
) : User() {}

class AdminManager(private val conn: Connection) {
    private val TableCreate =
        "CREATE TABLE IF NOT EXISTS admins (" +
        "    id SERIAL PRIMARY KEY," +
        "    name TEXT NOT NULL DEFAULT ''," +
        "    login TEXT NOT NULL DEFAULT ''," +
        "    password TEXT NOT NULL DEFAULT ''" +
        ");"
    private val SelectById = "SELECT * FROM admins WHERE id = ?"
    private val SelectAll = "SELECT * FROM admins;"
    private val Insert = "INSERT INTO admins (name, login, password)" +
            "VALUES (?, ?, ?) RETURNING id;"
    private val Update = "UPDATE admins SET name = ?, login = ?, password = ?" +
            " WHERE id = ? RETURNING id"
    private val DeleteById = "DELETE FROM admins WHERE id = ? RETURNING id"
    private val logger = LogsManager(this.javaClass.name)
    fun log(str: String) { this.logger.log(str) }
    fun err(str: String) {this.logger.err(str)}
    fun err(e: Exception) {this.logger.err(e.toString())}

    init {
        createTable()
    }

    fun createTable() {
        val statement = conn.createStatement()
        statement.execute(TableCreate)
    }

    fun getAll() : List<Admin> {
        val statement = conn.createStatement()
        statement.execute(SelectAll)
        val resSet = statement.resultSet
        return  Helpers().Parse().resultSetToAdminList(resSet)
    }

    fun getById(id: Int) : Admin {
        val statement = conn.prepareStatement(SelectById)
        statement.setInt(1, id)
        statement.execute()
        val resSet = statement.resultSet
        val res = Helpers().Parse().resultSetToAdmin(resSet)
        if (res != null) return res
        else throw Exception("(AdminManager.getById) No admin with id = $id found.")
    }

    fun add(admin: Admin) : Int {
        val statement = conn.prepareStatement(Insert)
        statement.setString(1, admin.name())
        statement.setString(2, admin.login())
        statement.setString(3, admin.password())
        statement.execute()
        val res = statement.resultSet
        return if (res.next()) {
            res.getInt("id")
        } else {
            -1
        }
    }

    fun change(admin: Admin) : Int {
        val statement = conn.prepareStatement(Update)
        statement.setString(1, admin.name())
        statement.setString(2, admin.login())
        statement.setString(3, admin.password())
        statement.execute()
        val resSet = statement.resultSet
        if (resSet.next()) return resSet.getInt("id")
        else throw Exception("(AdminManager.change) No admin with id = ${admin.id()} found.")
    }

    fun deleteById(id: Int) : Int {
        val statement = conn.prepareStatement(DeleteById)
        statement.setInt(1, id)
        statement.execute()
        val resSet = statement.resultSet
        if (resSet.next()) return resSet.getInt("id")
        else throw Exception("(AdminManager.deleteById) No admin with id = $id found.")
    }
}