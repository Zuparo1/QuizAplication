package com.example.quizaplication.model.quizDataClass

data class MultipleChoiceData (
    val prompt : String = "",
    val options : Array<String> = arrayOf(),
    val correct : String = ""
)
