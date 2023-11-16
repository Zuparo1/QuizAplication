package com.example.quizaplication.screens

import androidx.annotation.StringRes

data class SettingsState (
    val username: String = "",
    @StringRes val errorMessage: Int = 0
)