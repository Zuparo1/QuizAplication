package com.example.quizaplication.screens.signup

import androidx.annotation.StringRes

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val passwordCheck: String = "",
    @StringRes val errorMessage: Int = 0
)