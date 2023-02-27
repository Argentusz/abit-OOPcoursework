package edu.maxservices.plugins
import edu.maxservices.models.Exams
import edu.maxservices.models.Student
import java.sql.Array
import java.sql.Connection
import java.sql.ResultSet

class Helpers {
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
                if (value != 0) {
                    res[Exams.values()[i]] = value
                }
                i++
            }
            return res
        }

        fun mListToIntArraySQL(conn: Connection, mList: MutableList<Int>) : Array {
            return conn.createArrayOf("integer", mList.toTypedArray())
        }

        fun examsHashMapTpIntArraySQL(conn: Connection, exams: HashMap<Exams, Int>) : Array {
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
        fun resultSetToStudentsList(resSet: ResultSet) : List<Student> {
            val res = mutableListOf<Student>()
            while(resSet.next()) {
                val std = Student(
                    resSet.getInt("id"),
                    resSet.getString("name"),
                    resSet.getString("login"),
                    resSet.getString("password"),
                    Helpers().Convert().resulSetArrayToMutableInt(resSet, "applies"),
                    Helpers().Convert().resultSetToExamsHashMap(resSet)
                )
                res.add(std)
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
                    Helpers().Convert().resulSetArrayToMutableInt(resSet, "applies"),
                    Helpers().Convert().resultSetToExamsHashMap(resSet)
                )
            } else {
                null
            }
        }
    }
}