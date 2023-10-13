package com.example.quizaplication.screens.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizaplication.R
import com.example.quizaplication.common.ext.isValidEmail
import com.example.quizaplication.common.ext.isValidPassoword
import com.example.quizaplication.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
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
    fun onPasswordCheckChange(newValue: String) {
        uiState.value = uiState.value.copy(passwordCheck = newValue)
    }

    fun onSignUpClick(/*loggedIn: () -> Unit*/) {
        if (!email.isValidEmail()) {
            uiState.value = uiState.value.copy(errorMessage = R.string.email_error)
            return
        } else if (!password.isValidPassoword()) {
            uiState.value = uiState.value.copy(errorMessage = R.string.password_error)
            return
        } else if (!(password == uiState.value.passwordCheck)) {
            uiState.value = uiState.value.copy(errorMessage = R.string.password_check_error)
            return
        }

        viewModelScope.launch {
            try {
                accountService.createAccount(email, password) { error ->
                    if (error == null)
                        print("logged in")//loggedIn()
                }
            } catch(e: Exception) {
                uiState.value = uiState.value.copy(errorMessage = R.string.could_not_create_account)
            }
        }
    }
}