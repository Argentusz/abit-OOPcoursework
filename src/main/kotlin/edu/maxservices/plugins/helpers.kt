package edu.maxservices.plugins
import edu.maxservices.models.Student
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
                    hashMapOf() //TODO
                )
                res.add(std)
            }
            return res
        }
    }
}