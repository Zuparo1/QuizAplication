package com.example.quizaplication.screens.settings

import androidx.annotation.StringRes

data class SettingsState (
    val username: String = "",
    @StringRes val errorMessage: Int = 0
)