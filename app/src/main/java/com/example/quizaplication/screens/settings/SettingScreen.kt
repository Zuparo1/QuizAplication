package com.example.quizaplication.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizaplication.R
import com.example.quizaplication.navigation.TopNavBar
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    val fieldModifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 4.dp)
    val firebaseAuth = FirebaseAuth.getInstance()
    val user = firebaseAuth.currentUser

    val pageTitle = "Settings"
    var changingUsername by remember { mutableStateOf(false)}
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopNavBar(navController,pageTitle)
        Text(text = "Settings",
            style = TextStyle(
                fontSize = 46.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        if (changingUsername) {
            UsernameField(uiState.username, viewModel::onUsernameChange, fieldModifier)
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.buttonColor)
                ),
                onClick = {
                if (user != null) {
                    viewModel.onUsernameChangeClick(id = user.uid, completed = { changingUsername = false })
                }
            }) {
                Text(text = "Change")
            }
        } else {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.buttonColor)
                ),
                onClick = { changingUsername = true }) {
                Text(text = "Change username")
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