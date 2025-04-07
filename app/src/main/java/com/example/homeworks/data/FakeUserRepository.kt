package com.example.homeworks.data

import com.example.homeworks.domain.model.User
import com.example.homeworks.domain.repository.UserRepository

class FakeUserRepository : UserRepository {

    private val users = mutableListOf(
        User("Ivan", "Ivanov"),
        User("Petr", "Petrov"),
        User("Anna", "Smirnova")
    )

    override fun getUsers(): List<User> = users.toList()

    override fun deleteUser(firstName: String, lastName: String): Boolean {
        val user = users.find { it.firstName == firstName && it.lastName == lastName }
        return if (user != null) {
            users.remove(user)
            true
        } else {
            false
        }
    }
}
