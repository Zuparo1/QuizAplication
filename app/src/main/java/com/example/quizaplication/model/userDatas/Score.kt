package com.example.quizaplication.model.userDatas

data class Score(
    var history: Long = 0,
    var math: Long = 0,
    var science: Long = 0,
    var programing: Long = 0
)
