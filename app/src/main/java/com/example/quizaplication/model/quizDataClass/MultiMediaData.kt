package com.example.quizaplication.model.quizDataClass

data class MultiMediaData (
   val questionText : String,
    val options : List<String>,
    val correctAnswer : String,
    val multimediaUrl : String,
    val multimeidaType : String
)
