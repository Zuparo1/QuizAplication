package com.example.quizaplication.screens.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizaplication.R
import com.example.quizaplication.common.emailErrorSwitch
import com.example.quizaplication.common.ext.isValidEmail
import com.example.quizaplication.common.ext.isValidPassword
import com.example.quizaplication.common.ext.isValidUsername
import com.example.quizaplication.common.passwordErrorSwitch
import com.example.quizaplication.common.usernameErrorSwitch
import com.example.quizaplication.model.UserData
import com.example.quizaplication.service.AccountService
import com.example.quizaplication.service.UserDataService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    private val userDataService: UserDataService,
) : ViewModel() {
    var uiState = mutableStateOf(SignUpState())
        private set

    private val username
        get() = uiState.value.username
    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onUsernameChange(newValue: String) {
        uiState.value = uiState.value.copy(username = newValue)
    }
    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }
    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }
    fun onPasswordCheckChange(newValue: String) {
        uiState.value = uiState.value.copy(passwordCheck = newValue)
    }

    fun onSignUpClick(loggedIn: () -> Unit) {
        if (username.isValidUsername() == 1 || username.isValidUsername() == 2 || username.isValidUsername() == 3 || username.isValidUsername() == 4) {
            uiState.value = uiState.value.copy(errorMessage = usernameErrorSwitch(username.isValidUsername()))
        } else  if (email.isValidEmail() == 1 || email.isValidEmail() == 2) {
            uiState.value = uiState.value.copy(errorMessage = emailErrorSwitch(email.isValidEmail()))
            return
        } else if (password.isValidPassword() == 1 || password.isValidPassword() == 2 || password.isValidPassword() == 3
            || password.isValidPassword() == 4 || password.isValidPassword() == 5) {
            uiState.value = uiState.value.copy(errorMessage = passwordErrorSwitch(password.isValidPassword()))
            return
        } else if (password != uiState.value.passwordCheck) {
            uiState.value = uiState.value.copy(errorMessage = R.string.password_check_error)
            return
        }

        viewModelScope.launch {
            try {
                accountService.createAccount(email, password) { error ->
                    if (error == null) {
                        viewModelScope.launch {
                            userDataService.createUserData(accountService.currentUserId, UserData(userName = username))
                        }
                        loggedIn()
                    }
                }
            } catch(e: Exception) {
                uiState.value = uiState.value.copy(errorMessage = R.string.could_not_create_account)
            }
        }
    }
}