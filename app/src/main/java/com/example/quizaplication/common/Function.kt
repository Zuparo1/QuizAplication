package com.example.quizaplication.common

import com.example.quizaplication.R

fun usernameErrorSwitch(errorId: Int): Int {
    when (errorId) {
        1 -> return R.string.username_blank_error
        2 -> return R.string.username_contains_space_error
        3 -> return R.string.username_contains_newline_error
        4 -> return R.string.username_too_many_characters_error
        5 -> return R.string.username_too_few_characters_error
    }
    return R.string.unknown_username_error
}

fun emailErrorSwitch(errorId: Int): Int {
    when (errorId) {
        1 -> return R.string.email_blank_error
        2 -> return R.string.email_wrong_format_error
    }
    return R.string.unknown_email_error
}

fun passwordErrorSwitch(errorId: Int): Int {
    when (errorId) {
        1 -> return R.string.password_blank_error
        2 -> return R.string.password_contains_space_error
        3 -> return R.string.password_too_few_characters_error
        4 -> return R.string.password_too_many_characters_error
        5 -> return R.string.password_invalid_format_error
    }
    return R.string.unknown_password_error
}