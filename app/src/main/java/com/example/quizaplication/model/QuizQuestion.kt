package com.example.quizaplication.model

data class QuizQuestion (
    val prompt : String = "",
    val options : List<String> = listOf(),
    val correct : String = ""
)