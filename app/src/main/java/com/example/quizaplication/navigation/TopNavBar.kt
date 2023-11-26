package com.example.quizaplication.navigation
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.quizaplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(navController: NavController, pageTitle : String) {
    Column {
        TopAppBar(
            title = {
                Text(
                    pageTitle,
                    color = colorResource(id = R.color.primaryTextColor))
            },
            navigationIcon = {
                IconButton(onClick = {navController.popBackStack()}) {
                    Icon(Icons.Outlined.ArrowBack, "Pop backstack",
                        tint = Color(R.color.primaryTextColor))
                }
            }, actions = {
                IconButton(onClick = {navController
                    .navigate(route = Screen.Leaderboard.route)}) {
                    Icon(
                        painterResource(id = R.drawable.icon_leaderboard),
                        contentDescription = "Leaderboard",
                        tint = Color(R.color.primaryTextColor))
                }
                IconButton(onClick = {navController
                    .navigate(route = Screen.Setting.route) }) {
                    Icon(Icons.Outlined.Settings, "Settings Screen",
                        tint = Color(R.color.primaryTextColor))
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors
                (containerColor = Color.LightGray),
        )
    }
}