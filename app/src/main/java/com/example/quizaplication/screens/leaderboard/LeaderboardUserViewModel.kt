package com.example.quizaplication.screens.leaderboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizaplication.service.UserDataService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardUserViewModel @Inject constructor(
    private val userDataService: UserDataService
) : ViewModel() {
    var leaderboardUserState = mutableStateOf(LeaderboardUserState())
        private set

    private val history
        get() = leaderboardUserState.value.history
    private val math
        get() = leaderboardUserState.value.math
    private val science
        get() = leaderboardUserState.value.science
    private val programing
        get() = leaderboardUserState.value.programing

    fun getScore(id: String, type: String) {
        viewModelScope.launch {
            val score = userDataService.getUserScores(id, type)

            leaderboardUserState.value = leaderboardUserState.value.copy(history = score.history)
            leaderboardUserState.value = leaderboardUserState.value.copy(math = score.math)
            leaderboardUserState.value = leaderboardUserState.value.copy(science = score.science)
            leaderboardUserState.value = leaderboardUserState.value.copy(programing = score.programing)
        }
    }
}