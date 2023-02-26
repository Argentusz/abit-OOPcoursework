package edu.maxservices.models

import kotlinx.serialization.Serializable

@Serializable
open class User(
    private val _id: Int,
    private val _name: String,
    private val _login: String,
    private val _password: String,
) {
    fun getId() : Int {
        return _id
    }
    fun getName() : String {
        return _name
    }
    fun getLogin() : String {
        return _login
    }
    fun getPassword() : String {
        return _password
    }
}