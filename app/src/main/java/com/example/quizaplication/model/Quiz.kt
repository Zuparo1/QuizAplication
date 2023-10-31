package com.example.quizaplication.model

import com.google.firebase.firestore.DocumentId

data class Quiz(
    //val name : String,
    val questions : Map<String,Question>
)