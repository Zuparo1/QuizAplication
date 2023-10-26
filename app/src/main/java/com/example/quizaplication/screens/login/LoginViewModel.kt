package com.example.quizaplication.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizaplication.R
import com.example.quizaplication.common.emailErrorSwitch
import com.example.quizaplication.common.ext.isValidEmail
import com.example.quizaplication.common.ext.isValidPassword
import com.example.quizaplication.common.passwordErrorSwitch
import com.example.quizaplication.screens.signup.SignUpState
import com.example.quizaplication.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
) : ViewModel() {
    var uiState = mutableStateOf(SignUpState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }
    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onLoginClick(loggedIn: () -> Unit) {
        if (email.isValidEmail() == 1 || email.isValidEmail() == 2) {
            uiState.value = uiState.value.copy(errorMessage = emailErrorSwitch(email.isValidEmail()))
            return
        } else if (password.isValidPassword() == 1 || password.isValidPassword() == 2 || password.isValidPassword() == 3 || password.isValidPassword() == 4) {
            uiState.value = uiState.value.copy(errorMessage = passwordErrorSwitch(password.isValidPassword()))
            return
        }

        viewModelScope.launch {
            try {
                accountService.authenticate(email, password) { error ->
                    if (error == null)
                        loggedIn()
                }
            } catch(e: Exception) {
                uiState.value = uiState.value.copy(errorMessage = R.string.could_not_login)
            }
        }
    }
}