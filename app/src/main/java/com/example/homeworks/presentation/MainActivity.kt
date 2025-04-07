package com.example.homeworks.presentation

import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import com.example.homeworks.data.FakeUserRepository
import com.example.homeworks.domain.usecase.DeleteUserUseCase
import com.example.homeworks.R

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Связываем UI
        val firstNameInput = findViewById<EditText>(R.id.firstNameInput)
        val lastNameInput = findViewById<EditText>(R.id.lastNameInput)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val statusText = findViewById<TextView>(R.id.statusText)
        val userList = findViewById<TextView>(R.id.userList)

        // Создаём репозиторий и юзкейс
        val repository = FakeUserRepository()
        val deleteUseCase = DeleteUserUseCase(repository)
        viewModel = UserViewModel(repository, deleteUseCase)

        // Кнопка удаления
        deleteButton.setOnClickListener {
            val first = firstNameInput.text.toString()
            val last = lastNameInput.text.toString()
            viewModel.deleteUser(first, last)
        }

        // Наблюдаем за сообщениями
        viewModel.message.observe(this, Observer { message ->
            statusText.text = message
        })

        // Наблюдаем за списком
        viewModel.users.observe(this, Observer { users ->
            userList.text = users.joinToString("\n") { "${it.firstName} ${it.lastName}" }
        })
    }
}
