package com.example.quizaplication.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizaplication.R
import com.example.quizaplication.common.ext.isValidUsername
import com.example.quizaplication.common.usernameErrorSwitch
import com.example.quizaplication.service.UserDataService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userDataService: UserDataService,
) : ViewModel() {
    var uiState = mutableStateOf(SettingsState())
        private set

    private val username
        get() = uiState.value.username

    fun onUsernameChange(newValue: String) {
        uiState.value = uiState.value.copy(username = newValue)
    }

    fun onUsernameChangeClick(id: String, completed: () -> Unit) {
        if (username.isValidUsername() == 1 || username.isValidUsername() == 2 || username.isValidUsername() == 3 || username.isValidUsername() == 4) {
            uiState.value = uiState.value.copy(errorMessage = usernameErrorSwitch(username.isValidUsername()))
        }
        viewModelScope.launch {
            try {
                userDataService.changeName(id, username) { error ->
                    if (error == null) {
                        uiState.value = uiState.value.copy(username = "")
                        completed()
                    }
                }
            } catch (e: Exception) {
                uiState.value = uiState.value.copy(errorMessage = R.string.could_not_change_username)
            }
        }
    }
}