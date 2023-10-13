package com.example.quizaplication.common.ext

import android.util.Patterns
import java.util.regex.Pattern

private const val minPasswordLenght = 6
private const val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassoword(): Boolean {
    return this.isNotBlank() && this.length >= minPasswordLenght
            && Pattern.compile(passwordPattern).matcher(this).matches()
}