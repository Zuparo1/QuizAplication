package com.example.quizaplication.screens.login

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizaplication.R

@Composable
fun LoginScreen(
    loggedIn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    val fieldModifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 4.dp)

    Column (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.errorMessage !=0) {
            Text(text = stringResource(id = uiState.errorMessage),
                Modifier.padding(vertical = 8.dp))
        }

        EmailField(uiState.email, viewModel::onEmailChange, fieldModifier)
        PasswordField(uiState.password, viewModel::onPasswordChange, fieldModifier)

        Button(
            onClick = { viewModel.onSignUpClick(loggedIn) },
            modifier = Modifier
                .padding(16.dp, 8.dp),
        ) {
            Text(text = "Login", fontSize = 16.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(email: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = email,
        onValueChange = {onNewValue(it)},
        placeholder = { Text(text = "Email") },
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") },
    )
}

@Composable
fun PasswordField(password: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    PasswordField(password, R.string.password, onNewValue, modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordField(password: String, @StringRes placeholder: Int, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        modifier = modifier,
        value = password,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(text = stringResource(id = placeholder)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
        //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )
}
