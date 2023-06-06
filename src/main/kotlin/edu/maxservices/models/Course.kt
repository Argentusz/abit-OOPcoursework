package edu.maxservices.models

import edu.maxservices.plugins.Helpers
import edu.maxservices.plugins.LogsManager
import kotlinx.serialization.Serializable
import java.sql.Connection
@Serializable
data class CourseForTable (
    private val id: Int,
    private val name: String,
    private val description: String,
    private val prevMinScore: Int,
    private val budgetPlaces: Int,
    private val commercePlaces: Int,
    private val universityName: String,
    private val planet: String,
    private val city: String,
    private val eExamDate: String,
    private val eExamAud: Int,
)

@Serializable
data class Course (
    private val id: Int,
    private val name: String,
    private val description: String,
    private val prevMinScore: Int,
    private val budgetPlaces: Int,
    private val commercePlaces: Int,
    private val requiredExams: List<Exams>,
    private val eExamDate: String,
    private val eExamAud: Int,
) {
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
    fun eExamDate() : String {
        return eExamDate
    }
    fun eExamAud() : Int {
        return eExamAud
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
                "    eExamDate TEXT NOT NULL DEFAULT ''," +
                "    eExamAud INTEGER NOT NULL DEFAULT 0" +
                ");"
    private val CoursesToStudentsTableCreate =
        "CREATE TABLE IF NOT EXISTS courses_to_students (" +
                "    course_id INTEGER REFERENCES courses(id)," +
                "    student_id INTEGER REFERENCES students(id)" +
                ");"
    private val SelectById = "SELECT * FROM courses WHERE id = ?"
    private val SelectAll = "SELECT * FROM courses;"
    private val Insert = "INSERT INTO courses (name, description, prevMinScore, budgetPlaces, commercePlaces, requiredExams, eexamdate, eexamaud)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;"
    private val Update = "UPDATE courses SET name = ?, description = ?, prevMinScore = ?, budgetPlaces = ?, commercePlaces = ?, requiredExams = ?, eexamdate = ?, eexamaud = ?" +
            " WHERE id = ?"
    private val DeleteById = "DELETE FROM courses_to_students WHERE course_id = ?; DELETE FROM courses WHERE id = ?; UPDATE universities SET coursesids = array_remove(coursesids, ?)"
    private val FindApplicants = "SELECT * FROM courses_to_students WHERE course_id = ?"
    private val NewApplicant = "INSERT INTO courses_to_students (course_id, student_id)" +
            "VALUES (?, ?)"
    private val GetUniversity = "SELECT * FROM universities WHERE ? = ANY(coursesids)"

    private val logger = LogsManager(this.javaClass.name)
    fun log(str: String) { this.logger.log(str) }
    fun err(str: String) {this.logger.err(str)}
    fun err(e: Exception) {this.logger.err(e.toString())}

    init {
        createTables()
    }

    fun createTables() {
        val statement = conn.createStatement()
        statement.execute(TableCreate)
        statement.execute(CoursesToStudentsTableCreate)
    }



    fun getAll() : List<Course> {
        val statement = conn.createStatement()
        statement.execute(SelectAll)
        val resSet = statement.resultSet
        return Helpers().Parse().resultSetToCourseList(resSet, conn)
    }

    fun getAllForTable() : List<CourseForTable> {
        val courseList = getAll()
        val res = mutableListOf<CourseForTable>()
        courseList.forEach {
            val university = getUniversity(it.id())
            res.add(
                CourseForTable(
                    id = it.id(),
                    name = it.name(),
                    description = it.description(),
                    prevMinScore = it.prevMinScore(),
                    budgetPlaces = it.budgetPlaces(),
                    commercePlaces = it.commercePlaces(),
                    universityName = university.name(),
                    planet = university.planet(),
                    city = university.city(),
                    eExamDate = it.eExamDate(),
                    eExamAud = it.eExamAud()
            ))
        }
        return res;
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
    fun add(course: Course, uid: Int) : Int {
        try {
            Helpers().Check().newCourseCheck(course, uid, conn)
        } catch (e: Exception) {
            throw e
        }
        val statement = conn.prepareStatement(Insert)
        statement.setString(1, course.name())
        statement.setString(2, course.description())
        statement.setInt(3, course.prevMinScore())
        statement.setInt(4, course.budgetPlaces())
        statement.setInt(5, course.commercePlaces())
        statement.setArray(6, Helpers().Convert().examsListToIntArraySQL(course.requiredExams(), conn))
        statement.setString(7, course.eExamDate())
        statement.setInt(8, course.eExamAud())
        statement.execute()
        val res = statement.resultSet
        if (res.next()) {
            val id = res.getInt("id")
            UniversityManager(conn).addNewCourse(id, uid)
        } else {
            throw Exception("Unexpected error while inserting (courseManager.add())")
        }
        return -1
    }

    fun change(course: Course) {
        val statement = conn.prepareStatement(Update)
        statement.setString(1, course.name())
        statement.setString(2, course.description())
        statement.setInt(3, course.prevMinScore())
        statement.setInt(4, course.budgetPlaces())
        statement.setInt(5, course.commercePlaces())
        statement.setInt(8, course.id())
        statement.execute()
    }

    fun deleteById(id: Int) : Int {
        val statement = conn.prepareStatement(DeleteById)
        statement.setInt(1, id)
        statement.setInt(2, id)
        statement.setInt(3, id)
        statement.execute()
//        val resSet = statement.resultSet
//        if (resSet.next()) return resSet.getInt("id")
//        else throw Exception("(courseManager.deleteById) No course with id = $id found.")
        return 0
    }

    fun findApplicants(courseId: Int) : List<Student> {
        val statement = conn.prepareStatement(FindApplicants)
        statement.setInt(1, courseId)
        statement.execute()
        val resSet = statement.resultSet
        val sids = mutableListOf<Int>()
        while (resSet.next()) {
            sids.add(resSet.getInt("student_id"))
        }
        val studentManager = StudentManager(conn)
        val res = mutableListOf<Student>()
        for (v in sids) {
            res.add(studentManager.getById(v))
        }
        return res
    }

    fun newApplicant(student: Student, course: Course, conn: Connection) {
        val statement = conn.prepareStatement(NewApplicant)
        statement.setInt(1, course.id())
        statement.setInt(2, student.id())
        statement.setInt(3, student.getScore(course.requiredExams()))
        statement.execute()
    }

    fun getUniversity(id: Int) : University {
        val statement = conn.prepareStatement(GetUniversity)
        statement.setInt(1, id)
        statement.execute()
        val resSet = statement.resultSet
        val res = Helpers().Parse().resultSetToUniversity(resSet, conn )
        if (res != null) return res
        else throw Exception("(CourseManager.getUniversity) No university found.")
    }
}