package com.example.quizaplication.screens.login

import androidx.annotation.StringRes

data class LoginState(
    val email: String = "",
    val password: String = "",
    @StringRes val errorMessage: Int = 0
)