package com.example.quizaplication.screens.leaderboard

data class LeaderboardUserState(
    var history: Long? = 0,
    var math: Long? = 0,
    var science: Long? = 0,
    var programing: Long? = 0
)