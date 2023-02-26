package edu.maxservices.models

data class University (
    private val id: Int,
    private val name: String,
    private val login: String,
    private val password: String,
    private val courses: List<Course>
)