package edu.maxservices.models


sealed class User() {
    abstract val id: Int
    abstract val name: String
    abstract val login: String
    abstract val password: String
    fun id() : Int {
        return id
    }
    fun name() : String {
        return name
    }
    fun login() : String {
        return login
    }
    fun password() : String {
        return password
    }
}