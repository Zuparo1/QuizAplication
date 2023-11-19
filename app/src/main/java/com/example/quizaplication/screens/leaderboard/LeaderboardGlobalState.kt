package com.example.quizaplication.screens.leaderboard

import com.example.quizaplication.model.leaderboardData.UserScore

data class LeaderboardGlobalState(
    val userScores: MutableList<UserScore>? = mutableListOf()
)