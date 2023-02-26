package edu.maxservices.models

class University (
    private val id: Int,
    private val name: String,
    private val login: String,
    private val password: String,
    private val courses: List<Course>
) {
    fun getCourses(): List<Course> {
        return courses
    }
}