package edu.maxservices.models

import edu.maxservices.plugins.Helpers
import kotlinx.serialization.Serializable
import java.sql.Connection

@Serializable
data class Course (
    private val id: Int,
    private val name: String,
    private val description: String,
    private val prevMinScore: Int,
    private val budgetPlaces: Int,
    private val commercePlaces: Int,
    private val requiredExams: List<Exams>,
    private val applicants: MutableList<Student>,
) {
    // Add applicant to list and
    // add this course to applicant's list
    fun newApplicant(newbie: Student) {
        applicants.add(newbie)
        newbie.getLinkApplies().add(this.id)
    }
    // Sort applicants according to exams required in this course
    fun sortApplicants() : List<Pair<Student, Int>> {
        val apls : MutableList<Pair<Student, Int>> = mutableListOf()
        for (i in 0 until applicants.size) {
            apls.add(i, Pair(applicants[i], applicants[i].getScore(requiredExams)))
        }
        return apls.sortedWith(compareBy { it.second }).asReversed().toList()
    }
    // Get applicants guaranteed to be on a budget
    fun budgetApplicants() : List<Pair<Student, Int>> {
        return sortApplicants().slice(0 until budgetPlaces)
    }
    // Get applicants guaranteed to be on commerce
    fun commerceApplicants() : List<Pair<Student, Int>> {
        return sortApplicants().slice(budgetPlaces until budgetPlaces + commercePlaces)
    }
    // Getters
    fun id() : Int {
        return id
    }
    fun name() : String {
        return name
    }
    fun description() : String {
        return description
    }
    fun prevMinScore() : Int {
        return prevMinScore
    }
    fun budgetPlaces() : Int {
        return budgetPlaces
    }
    fun commercePlaces() : Int {
        return commercePlaces
    }
    fun requiredExams(): List<Exams> {
        return requiredExams.toList()
    }
    fun applicants(): List<Student> {
        return applicants.toList()
    }
}

class CourseManager(private val conn : Connection) {
    private val TableCreate =
        "CREATE TABLE IF NOT EXISTS courses (" +
                "    id SERIAL PRIMARY KEY," +
                "    name TEXT NOT NULL DEFAULT ''," +
                "    description TEXT NOT NULL DEFAULT ''," +
                "    prevMinScore INTEGER NOT NULL DEFAULT 0," +
                "    budgetPlaces INTEGER NOT NULL DEFAULT 0," +
                "    commercePlaces INTEGER NOT NULL DEFAULT 0," +
                "    requiredExams INTEGER[] DEFAULT array[]::integer[]," +
                "    applicants INTEGER[] DEFAULT array[]::integer[]" +
                ");"
    private val SelectById = "SELECT * FROM courses WHERE id = ?"
    private val SelectAll = "SELECT * FROM courses;"
    private val Insert = "INSERT INTO courses (name, description, prevMinScore, budgetPlaces, commercePlaces, requiredExams, applicants)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id;"
    private val Update = "UPDATE courses SET name = ?, description = ?, prevMinScore = ?, budgetPlaces = ?, commercePlaces = ?, requiredExams = ?, applicants = ?" +
            " WHERE id = ?"
    private val DeleteById = "DELETE FROM courses WHERE id = ? RETURNING id"

    init {
        createTable()
    }

    fun createTable() {
        val statement = conn.createStatement()
        statement.execute(TableCreate)
    }

    fun getAll() : List<Course> {
        val statement = conn.createStatement()
        statement.execute(SelectAll)
        val resSet = statement.resultSet
        return Helpers().Parse().resultSetToCourseList(resSet, conn)
    }

    fun getById(id: Int) : Course {
        val statement = conn.prepareStatement(SelectById)
        statement.setInt(1, id)
        statement.execute()
        val resSet = statement.resultSet
        val res = Helpers().Parse().resultSetToCourse(resSet, conn)
        if (res != null) return res
        else throw Exception("(CourseManager.getById) No course with id = $id found.")
    }
    fun add(course: Course) : Int {
        val statement = conn.prepareStatement(Insert)
        statement.setString(1, course.name())
        statement.setString(2, course.description())
        statement.setInt(3, course.prevMinScore())
        statement.setInt(4, course.budgetPlaces())
        statement.setInt(5, course.commercePlaces())
        statement.setArray(6, Helpers().Convert().examsListToIntArraySQL(course.requiredExams(), conn))
        statement.setArray(7, Helpers().Convert().studentListToIntArraySQL(course.applicants(), conn))
        statement.execute()
        val res = statement.resultSet
        return if (res.next()) {
            res.getInt("id")
        } else {
            -1
        }
    }

    fun change(course: Course) {
        val statement = conn.prepareStatement(Update)
        statement.setString(1, course.name())
        statement.setString(2, course.description())
        statement.setInt(3, course.prevMinScore())
        statement.setInt(4, course.budgetPlaces())
        statement.setInt(5, course.commercePlaces())
        statement.setArray(6, Helpers().Convert().examsListToIntArraySQL(course.requiredExams(), conn))
        statement.setArray(7, Helpers().Convert().studentListToIntArraySQL(course.applicants(), conn))
        statement.setInt(8, course.id())
        statement.execute()
    }

    fun deleteById(id: Int) : Int {
        val statement = conn.prepareStatement(DeleteById)
        statement.setInt(1, id)
        statement.execute()
        val resSet = statement.resultSet
        if (resSet.next()) return resSet.getInt("id")
        else throw Exception("(courseManager.deleteById) No course with id = $id found.")
    }
}