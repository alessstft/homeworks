package com.example.homeworks.domain.usecase

import com.example.homeworks.domain.repository.UserRepository

class DeleteUserUseCase(private val repository: UserRepository) {
    fun execute(firstName: String, lastName: String): Boolean {
        return repository.deleteUser(firstName, lastName)
    }
}