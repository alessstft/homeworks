package com.example.homeworks.domain.repository

import com.example.homeworks.domain.model.User

interface UserRepository {
    fun getUsers(): List<User>
    fun deleteUser(firstName: String, lastName: String): Boolean
}