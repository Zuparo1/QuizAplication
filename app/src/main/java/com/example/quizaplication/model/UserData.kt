package com.example.quizaplication.model

import com.example.quizaplication.model.userDatas.MultiMedia
import com.example.quizaplication.model.userDatas.MultipleChoiceQuiz
import com.example.quizaplication.model.userDatas.TextInput
import com.example.quizaplication.model.userDatas.TrueOrFalse

data class UserData(
    val userName: String = "",
    val multipleChoiceQuiz: MultipleChoiceQuiz = MultipleChoiceQuiz(),
    val trueOrFalse: TrueOrFalse = TrueOrFalse(),
    val textInput: TextInput = TextInput(),
    val multiMedia: MultiMedia = MultiMedia()
)