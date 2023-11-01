package com.example.quizaplication.model

data class QuizQuestion (
    val prompt : String = "",
    val options : Array<String> = arrayOf(),
    val correct : String = ""
)