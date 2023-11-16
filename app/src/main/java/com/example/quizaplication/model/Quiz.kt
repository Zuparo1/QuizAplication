package com.example.quizaplication.model

import com.example.quizaplication.model.quizDataClass.MultipleChoiceData

data class Quiz @JvmOverloads constructor(
    val questions : Map<String, MultipleChoiceData>
)