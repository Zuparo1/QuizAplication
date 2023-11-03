package com.example.quizaplication.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizaplication.screens.home.HomeState
import com.example.quizaplication.service.UserDataService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userDataService: UserDataService
): ViewModel() {
    var homeState = mutableStateOf(HomeState())
        private set

    private val userName
        get() = homeState.value.userName

    fun getUserName(id: String) {
        viewModelScope.launch {
            homeState.value = homeState.value.copy(userName = userDataService.getName(id))
        }
    }
}