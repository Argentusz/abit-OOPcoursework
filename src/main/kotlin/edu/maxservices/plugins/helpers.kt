package edu.maxservices.plugins
import edu.maxservices.models.*
import kotlinx.serialization.Serializable
import java.sql.Array
import java.sql.Connection
import java.sql.ResultSet

@Serializable
data class courseFull(
    val id: Int,
    val name: String,
    val uName: String,
    val prevMinScore: Int,
    val budgetPlaces: Int,
    val commercePlaces: Int,
    val planet: String,
    val city: String,
) {}

class Helpers {

    inner class Check {
        fun loginCheck(auth: Auth) : Boolean {
            if (authAllowedSymbols(auth)) return true
            else throw Exception("Symbols are not allowed")
        }
        fun registrationCheck(auth: Auth) : Boolean {
            return authAllowedSymbols(auth) && passwordStrength(auth.password)
        }
        fun passwordStrength(password: String) : Boolean {
            return password.length >= 6
        }
        fun authAllowedSymbols(auth: Auth) : Boolean {
            return  allowedSymbols(auth.login) &&
                    allowedSymbols(auth.password) &&
                    allowedSymbols(auth.name, true) &&
                    (auth.role in 0..2)
        }
        fun allowedSymbols(str: String, spaceAllowed : Boolean = false) : Boolean {
            str.forEach {
                if(!it.isLetterOrDigit()) {
                    if (!spaceAllowed || it != ' ') return false
                }
            }
            return true
        }
        fun examsCheck(scores: HashMap<Exams, Int>) : Boolean {
            for ((key, value) in scores) {
                if (value > 100 || value < 0) {
                    return false
                }
            }
            return true
        }
    }
    inner class Convert {
        fun resulSetArrayToMutableInt(resSet: ResultSet, columnLabel: String) : MutableList<Int> {
            val arrRSet = resSet.getArray(columnLabel).resultSet
            val res = mutableListOf<Int>()
            while(arrRSet.next()) {
                res.add(arrRSet.getInt(2))
            }
            return res
        }

        fun resultSetToExamsHashMap(resSet: ResultSet): HashMap<Exams, Int> {
            val arrRSet = resSet.getArray("exams").resultSet
            val res = hashMapOf<Exams, Int>()
            var i = 0
            while (arrRSet.next()) {
                val value = arrRSet.getInt(2)
                res[Exams.values()[i]] = value
                i++
            }
            return res
        }

        fun resultSetToStudentMList(resSet: ResultSet, conn: Connection) : MutableList<Student> {
            val arrRSet = resSet.getArray("applicants").resultSet
            val res = mutableListOf<Student>()
            while (arrRSet.next()) {
                res.add(
                    StudentManager(conn).getById(arrRSet.getInt(2))
                )
            }
            return res
        }

        fun resultSetToExamsList(resSet: ResultSet): List<Exams> {
            val arrRSet = resSet.getArray("requiredExams").resultSet
            val res = mutableListOf<Exams>()
            while (arrRSet.next()) {
                val value = arrRSet.getInt(2)
                res.add(Exams.values()[value])
            }
            return res
        }

        fun mListToIntArraySQL(conn: Connection, mList: MutableList<Int>) : Array {
            return conn.createArrayOf("integer", mList.toTypedArray())
        }



        fun examsListToIntArraySQL(list: List<Exams>, conn: Connection) : Array {
            val numList = mutableListOf<Int>()
            for (exam in list) {
                numList.add(exam.ordinal)
            }
            return conn.createArrayOf("integer", numList.toTypedArray())
        }

        fun courseListToIntArraySQL(list: List<Course>, conn: Connection) : Array {
            val numList = mutableListOf<Int>()
            for (course in list) {
                numList.add(course.id())
            }
            return conn.createArrayOf("integer", numList.toTypedArray())

        }

        fun studentListToIntArraySQL(list: List<Student>, conn: Connection) : Array {
            val res = mutableListOf<Int>()
            for (student in list) {
                res.add(student.id())
            }
            return conn.createArrayOf("integer", res.toTypedArray())
        }

        fun examsHashMapToIntArraySQL(conn: Connection, exams: HashMap<Exams, Int>) : Array {
            val list = mutableListOf<Int>()
            enumValues<Exams>().forEach {
                if (exams.containsKey(it)) {
                    list.add(exams[it]!!)
                } else {
                    list.add(0)
                }
            }
            return conn.createArrayOf("integer", list.toTypedArray())
        }
    }

    inner class Parse {
        fun resultSetToCourseStudentList(resSet: ResultSet) : List<CoursesToStudents> {
            val res = mutableListOf<CoursesToStudents>()
            while(resSet.next()) {
                val crsStd = CoursesToStudents (
                    resSet.getInt("course_id"),
                    resSet.getInt("student_id")
                )
                res.add(crsStd)
            }
            return res
        }
        fun resultSetToStudentList(resSet: ResultSet) : List<Student> {
            val res = mutableListOf<Student>()
            while(resSet.next()) {
                val std = Student(
                    resSet.getInt("id"),
                    resSet.getString("name"),
                    resSet.getString("login"),
                    resSet.getString("password"),
                    Helpers().Convert().resultSetToExamsHashMap(resSet)
                )
                res.add(std)
            }
            return res
        }
        fun resultSetToCourseList(resSet: ResultSet, conn: Connection) : List<Course> {
            val res = mutableListOf<Course>()
            while(resSet.next()) {
                res.add(Course(
                    resSet.getInt("id"),
                    resSet.getString("name"),
                    resSet.getString("description"),
                    resSet.getInt("prevMinScore"),
                    resSet.getInt("budgetPlaces"),
                    resSet.getInt("commercePlaces"),
                    Helpers().Convert().resultSetToExamsList(resSet),
                ))
            }
            return res
        }
        fun resultSetArrayOfCourseIdsToCourseList(resSet: ResultSet, conn: Connection) : List<Course> {
            val res = mutableListOf<Course>()
            val courseManager = CourseManager(conn)
            while (resSet.next()) {
                val id = resSet.getInt(2)
                val course = courseManager.getById(id)
                res.add(course)
            }
            return res
        }

        fun resultSetToUniversityList(resSet: ResultSet, conn: Connection) : List<University> {
            val res = mutableListOf<University>()
            while(resSet.next()) {
                Helpers().Convert().resulSetArrayToMutableInt(resSet, "coursesIds")
                res.add(
                    University (
                        resSet.getInt("id"),
                        resSet.getString("name"),
                        resSet.getString("login"),
                        resSet.getString("password"),
                        Helpers().Parse().resultSetToCourseList(resSet, conn),
                        resSet.getString("planet"),
                        resSet.getString("city"),
                    )
                )
            }
            return res
        }

        fun resultSetToAdminList(resSet: ResultSet) : List<Admin> {
            val res = mutableListOf<Admin>()
            while (resSet.next()) {
                res.add(Admin(
                    resSet.getInt("id"),
                    resSet.getString("name"),
                    resSet.getString("login"),
                    resSet.getString("password")
                ))
            }
            return res
        }

        fun resultSetToStudent(resSet: ResultSet) : Student? {
            return if (resSet.next()) {
                Student(
                    resSet.getInt("id"),
                    resSet.getString("name"),
                    resSet.getString("login"),
                    resSet.getString("password"),
                    Helpers().Convert().resultSetToExamsHashMap(resSet)
                )
            } else {
                null
            }
        }


        fun resultSetToAdmin(resSet: ResultSet) : Admin? {
            return if (resSet.next()) {
                Admin(
                    resSet.getInt("id"),
                    resSet.getString("name"),
                    resSet.getString("login"),
                    resSet.getString("password")
                )
            } else {
                null
            }
        }

        fun resultSetToUniversity(resSet: ResultSet, conn: Connection) : University? {
            return if (resSet.next()) {
                University (
                    resSet.getInt("id"),
                    resSet.getString("name"),
                    resSet.getString("login"),
                    resSet.getString("password"),
                    Helpers().Parse().resultSetArrayOfCourseIdsToCourseList(resSet.getArray("coursesIds").resultSet, conn),
                    resSet.getString("planet"),
                    resSet.getString("city"),
                )
            } else {
                null
            }
        }
        fun resultSetToCourse(resSet: ResultSet, conn: Connection) : Course? {
            return if (resSet.next()) {
                Course(
                    resSet.getInt("id"),
                    resSet.getString("name"),
                    resSet.getString("description"),
                    resSet.getInt("prevMinScore"),
                    resSet.getInt("budgetPlaces"),
                    resSet.getInt("commercePlaces"),
                    Helpers().Convert().resultSetToExamsList(resSet),
                )
            } else {
                null
            }
        }
    }
}