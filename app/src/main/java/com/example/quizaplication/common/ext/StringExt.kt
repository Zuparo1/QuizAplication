package com.example.quizaplication.common.ext

import android.util.Patterns
import java.util.regex.Pattern

private const val minUsernameLength = 4
private const val maxUsernameLength = 20
private const val minPasswordLength = 6
private const val maxPasswordLength = 25
private const val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

fun String.isValidUsername(): Int {
    return if (this.isBlank()) {
        1 //Username must not be blank error
    } else if (this.contains(" ")) {
        2 //Username cannot contain a space error
    } else if (this.length > maxUsernameLength) {
        3 //Username cannot contain more than 18 characters error
    } else if (this.length < minUsernameLength) {
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

/* Deprecated
fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
*/

fun String.isValidPassword(): Int {
    return if (this.isBlank()) {
        1 //Password must not be blank error
    } else if (this.contains(" ")) {
        4 //Password cannot contain space error
    } else if (this.length < minPasswordLength) {
        3 //Password is too short error
    } else if (this.length > maxPasswordLength) {
        4 //Password is too long error
    } else if (!Pattern.compile(passwordPattern).matcher(this).matches()) {
        5 //Password must be valid format error
    } else {
        0 //Password is valid
    }
}

/* Deprecated
fun String.isValidPassword(): Boolean {
    return this.isNotBlank() && this.length >= minPasswordLength
            && Pattern.compile(passwordPattern).matcher(this).matches()
}
*/