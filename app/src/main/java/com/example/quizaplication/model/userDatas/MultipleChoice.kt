package com.example.quizaplication.model.userDatas

data class MultipleChoice(
    var historyBeginner: Long = 0,
    var historyIntermediate: Long = 0,
    var historyHard: Long = 0,
    var mathBeginner: Long = 0,
    var mathIntermediate: Long = 0,
    var mathHard: Long = 0,
    var scienceBeginner: Long = 0,
    var scienceIntermediate: Long = 0,
    var scienceHard: Long = 0,
    var programingBeginner: Long = 0,
    var programingIntermediate: Long = 0,
    var programingHard: Long = 0
)
