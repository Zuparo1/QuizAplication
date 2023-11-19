package com.example.quizaplication.screens.leaderboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizaplication.model.leaderboardData.UserScore
import com.example.quizaplication.service.UserDataService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardGlobalViewModel @Inject constructor(
    private val userDataService: UserDataService
) : ViewModel() {
    var leaderboardGlobalState = mutableStateOf(LeaderboardGlobalState())
        private set

    private val userScores
        get() = leaderboardGlobalState.value.userScores

    fun getScores(type: String, subject: String) {
        viewModelScope.launch {
            val tempScores = mutableListOf<UserScore>()
            val scores = userDataService.getScores(type, subject)

            scores.forEach { score ->
                tempScores.add(score)
            }
            leaderboardGlobalState.value = leaderboardGlobalState.value.copy(userScores = tempScores)
        }
    }
}