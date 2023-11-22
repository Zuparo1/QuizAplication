package com.example.quizaplication.model

import com.example.quizaplication.model.userDatas.MultiMedia
import com.example.quizaplication.model.userDatas.MultipleChoice
import com.example.quizaplication.model.userDatas.TextInput
import com.example.quizaplication.model.userDatas.TrueOrFalse

data class UserData(
    var userName: String = "",
    val multipleChoiceQuiz: MultipleChoice = MultipleChoice(),
    val multiMedia: MultiMedia = MultiMedia(),
    val textInput: TextInput = TextInput(),
    val trueOrFalse: TrueOrFalse = TrueOrFalse()
)