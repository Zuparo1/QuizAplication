package com.example.quizaplication.screens.signup

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizaplication.R
import com.example.quizaplication.navigation.TopNavBar

@Composable
fun SignUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    val fieldModifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 4.dp)
    Column {
        TopNavBar(navController,"")
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

            UsernameField(uiState.username, viewModel::onUsernameChange, fieldModifier)
            EmailField(uiState.email, viewModel::onEmailChange, fieldModifier)
            PasswordField(uiState.password, viewModel::onPasswordChange, fieldModifier)
            PasswordCheckField(uiState.passwordCheck, viewModel::onPasswordCheckChange, fieldModifier)

            Button(
                onClick = { viewModel.onSignUpClick(loggedIn = {navController.popBackStack()}) },
                modifier = Modifier
                    .padding(16.dp, 8.dp),
            ) {
                Text(text = "Register", fontSize = 16.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameField(username: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = username,
        onValueChange = {onNewValue(it)},
        placeholder = { Text(text = "Username") },
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Person") }
        )
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
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email")},
    )
}

@Composable
fun PasswordField(password: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    PasswordField(password, R.string.password, onNewValue, Icons.Default.Lock, "Lock", modifier)
}

@Composable
fun PasswordCheckField(passwordCheck: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    PasswordField(passwordCheck, R.string.password_check, onNewValue, Icons.Default.Refresh, "Refresh", modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordField(password: String, @StringRes placeholder: Int, onNewValue: (String) -> Unit, icon: ImageVector, iconDesc: String, modifier: Modifier = Modifier) {
    var isVisible by remember { mutableStateOf(false)}
    val visibilityIcon =
        if (isVisible) painterResource(R.drawable.icon_visibility)
        else painterResource(R.drawable.icon_visibility_off)

    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        modifier = modifier,
        value = password,
        onValueChange = { onNewValue(it) },
        placeholder = {Text(text = stringResource(id = placeholder))},
        leadingIcon = { Icon(imageVector = icon, contentDescription = iconDesc) },
        trailingIcon = {
            IconButton(onClick = { isVisible = !isVisible }) {
                Icon(painter = visibilityIcon, contentDescription = "Visibility")
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation
        )
}