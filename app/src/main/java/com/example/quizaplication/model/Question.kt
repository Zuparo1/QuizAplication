package com.example.quizaplication.model

data class Question(
    val prompt : String,
    val options : List<String>,
    val correct : String
)