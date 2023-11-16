package com.example.quizaplication.common.ext

import android.util.Patterns
import java.util.regex.Pattern

private const val MIN_USERNAME_LENGHT = 4
private const val MAX_USERNAME_LENGHT = 20
private const val MIN_PASSWORD_LENGHT = 6
private const val MAX_PASSWORD_LENGHT = 25
private const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

fun String.isValidUsername(): Int {
    return if (this.isBlank()) {
        1 //Username must not be blank error
    } else if (this.contains(" ")) {
        2 //Username cannot contain a space error
    } else if (this.length > MAX_USERNAME_LENGHT) {
        3 //Username cannot contain more than 18 characters error
    } else if (this.length < MIN_USERNAME_LENGHT) {
        4 //Username must contain 4 characters or more error
    } else {
        0 //Username is valid
    }
}

fun String.isValidEmail(): Int {
    return if (this.isBlank()) {
        1 //Email must not be blank error
    } else if (!Patterns.EMAIL_ADDRESS.matcher(this).matches()) {
        2 //Email must be valid format error
    } else {
        0 //Email is valid
    }
}

fun String.isValidPassword(): Int {
    return if (this.isBlank()) {
        1 //Password must not be blank error
    } else if (this.contains(" ")) {
        4 //Password cannot contain space error
    } else if (this.length < MIN_PASSWORD_LENGHT) {
        3 //Password is too short error
    } else if (this.length > MAX_PASSWORD_LENGHT) {
        4 //Password is too long error
    } else if (!Pattern.compile(PASSWORD_PATTERN).matcher(this).matches()) {
        5 //Password must be valid format error
    } else {
        0 //Password is valid
    }
}

fun String.lowerCaseFirstChar(): String {
    return this.replaceFirstChar { it.lowercase() }
}

fun String.upperCaseFirstChar(): String {
    return this.replaceFirstChar { it.uppercase() }
}