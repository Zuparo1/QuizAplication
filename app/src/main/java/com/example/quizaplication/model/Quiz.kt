package com.example.quizaplication.model

data class Quiz @JvmOverloads constructor(
    val questions : Map<String,QuizQuestion>
)