package edu.maxservices.models

import edu.maxservices.plugins.Helpers
import edu.maxservices.plugins.LogsManager
import edu.maxservices.plugins.courseFull
import kotlinx.serialization.Serializable
import java.sql.Connection

@Serializable
data class Student (
    override val id: Int,
    override val name: String,
    override val login: String,
    override val password: String,
    private val scores: HashMap<Exams, Int>,
) : User() {

    fun getScore(req: List<Exams>): Int {
        var res = 0
        for (i in req) {
            res += if (scores[i] != null) scores[i]!! else 0
        }
        return res
    }
    fun getScores() : HashMap<Exams, Int> {
        return scores
    }
}

@Serializable
data class CoursesToStudents(
    val courseId: Int,
    val studentId: Int,
)

class StudentManager(private val conn : Connection) {
    private val TableCreate =
        "CREATE TABLE IF NOT EXISTS students (" +
                "    id SERIAL PRIMARY KEY," +
                "    name TEXT NOT NULL DEFAULT ''," +
                "    login TEXT NOT NULL DEFAULT ''," +
                "    password TEXT NOT NULL DEFAULT ''," +
                "    exams INTEGER[] DEFAULT array[]::integer[]" +
                ");"
    private val SelectById = "SELECT * FROM students WHERE id = ?"
    private val SelectAll = "SELECT * FROM students;"
    private val Insert = "INSERT INTO students (name, login, password,exams)" +
            "VALUES (?, ?, ?, ?) RETURNING id;"
    private val Update = "UPDATE students SET name = ?, login = ?, password = ?, exams = ?" +
            " WHERE id = ?"
    private val DeleteById = "DELETE FROM students WHERE id = ? RETURNING id"
    private val FindApplies = "SELECT * FROM courses_to_students WHERE student_id = ?"
    private val NewApplicant = "INSERT INTO courses_to_students (course_id, student_id)" +
            "VALUES (?, ?)"
    private val DeleteApply = "DELETE FROM courses_to_students WHERE student_id = ? AND course_id = ? RETURNING student_id"
    private val CheckForApply = "SELECT * FROM courses_to_students WHERE student_id = ? AND course_id = ?"
    private val CheckForUnique = "SELECT * FROM students WHERE login = ?"
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

    fun getAll(): List<Student> {
        val statement = conn.createStatement()
        statement.execute(SelectAll)
        val resSet = statement.resultSet
        return Helpers().Parse().resultSetToStudentList(resSet)
    }

    fun getById(id: Int): Student {
        val statement = conn.prepareStatement(SelectById)
        statement.setInt(1, id)
        statement.execute()
        val resSet = statement.resultSet
        val res = Helpers().Parse().resultSetToStudent(resSet)
        if (res != null) return res
        else throw Exception("(StudentManager.getById) No student with id = $id found.")
    }

    fun add(student: Student) : Int {
        val statement = conn.prepareStatement(Insert)
        statement.setString(1, student.name())
        statement.setString(2, student.login())
        statement.setString(3, student.password())
        statement.setArray(4, Helpers().Convert().examsHashMapToIntArraySQL(conn, student.getScores()))
        statement.execute()
        val res = statement.resultSet
        return if (res.next()) {
            res.getInt("id")
        } else {
            -1
        }
    }

    fun change(student: Student) {
        val statement = conn.prepareStatement(Update)
        statement.setString(1, student.name())
        statement.setString(2, student.login())
        statement.setString(3, student.password())
        statement.setArray(4, Helpers().Convert().examsHashMapToIntArraySQL(conn, student.getScores()))
        statement.setInt(5, student.id())
        statement.execute()
    }

    fun deleteById(id: Int) : Int {
        val statement = conn.prepareStatement(DeleteById)
        statement.setInt(1, id)
        statement.execute()
        val resSet = statement.resultSet
        if (resSet.next()) return resSet.getInt("id")
        else throw Exception("(StudentManager.deleteById) No student with id = $id found.")
    }

    fun unApply(studentId: Int, courseId: Int) : Int {
        val statement = conn.prepareStatement(DeleteApply)
        statement.setInt(1, studentId)
        statement.setInt(2, courseId)
        statement.execute()
        val resSet = statement.resultSet
        if (resSet.next()) return resSet.getInt("student_id")
        else throw Exception("(StudentManager.unApply) No match student=$studentId course=$courseId found.")

    }
    fun newApply(studentId: Int, courseId: Int) {
        val checkStatement = conn.prepareStatement(CheckForApply)
        checkStatement.setInt(1, studentId)
        checkStatement.setInt(2, courseId)
        checkStatement.execute()
        val resSet = checkStatement.resultSet
        if (resSet.next()) {
            throw Exception("(StudentManager.newApply) Already have apply for student $studentId and course $courseId.")

        }

        val statement = conn.prepareStatement(NewApplicant)
        statement.setInt(1, courseId)
        statement.setInt(2, studentId)
        try {
            statement.execute()
        } catch (e: Exception) {
            throw Exception("(StudentManager.newApply) Could not make a new apply for student $studentId and course $courseId.")
        }
    }

    fun checkUniqueLogin(login: String) : Boolean {
     val statement = conn.prepareStatement(CheckForUnique)
     statement.setString(1, login)
     statement.execute()
     val resSet = statement.resultSet
     if (resSet.next()) {
         return false
     }
     return true
    }
    fun findApplies(id: Int) : List<courseFull> {

        val statement = conn.prepareStatement(FindApplies)
        statement.setInt(1, id)
        statement.execute()
        val resSet = statement.resultSet

        val crsStd = Helpers().Parse().resultSetToCourseStudentList(resSet)


        val res = mutableListOf<courseFull>()
        crsStd.forEach {

            val course = CourseManager(conn).getById(it.courseId)

            val university = CourseManager(conn).getUniversity(it.courseId)

            val resItem = courseFull(
                id = course.id(),
                name = course.name(),
                uName = university.name(),
                prevMinScore = course.prevMinScore(),
                budgetPlaces = course.budgetPlaces(),
                commercePlaces = course.commercePlaces(),
                planet = university.planet(),
                city = university.city(),
                eExamDate = course.eExamDate(),
                eExamAud = course.eExamAud()
            )
            res.add(resItem)
        }


        return res
    }
}
