package com.example.quizaplication.model

import com.example.quizaplication.model.userDatas.MultipleChoice

data class UserData(
    val userName: String = "",
    val multipleChoice: MultipleChoice = MultipleChoice()
)