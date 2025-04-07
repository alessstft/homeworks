package com.example.homeworks.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeworks.domain.model.User
import com.example.homeworks.domain.repository.UserRepository
import com.example.homeworks.domain.usecase.DeleteUserUseCase

class UserViewModel(
    private val repository: UserRepository,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        loadUsers()
    }

    fun loadUsers() {
        _users.value = repository.getUsers()
    }

    fun deleteUser(firstName: String, lastName: String) {
        val result = deleteUserUseCase.execute(firstName, lastName)
        if (result) {
            _message.value = "Пользователь удалён"
            loadUsers()
        } else {
            _message.value = "Пользователь не найден"
        }
    }
}
