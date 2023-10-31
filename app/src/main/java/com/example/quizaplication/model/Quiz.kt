package com.example.quizaplication.model

data class Quiz @JvmOverloads constructor(
    //val name : String,
    val questions : Map<String,QuizQuestion>
)