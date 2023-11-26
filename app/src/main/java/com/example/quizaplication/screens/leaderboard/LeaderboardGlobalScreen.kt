package com.example.quizaplication.screens.leaderboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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

@Composable
fun LeaderboardGlobalScreen(
    navController: NavController,
    documentPath: String,
    subjectPath: String,
    viewModel: LeaderboardGlobalViewModel = hiltViewModel()
) {
    val leaderboardGlobalState by viewModel.leaderboardGlobalState

    val pageTitle = "Leaderboard"

    LaunchedEffect(Unit) {
        viewModel.getScores(documentPath, subjectPath)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopNavBar(navController,pageTitle)
        Text(text = "Global",
            style = TextStyle(
                fontSize = 46.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            leaderboardGlobalState.userScores?.forEachIndexed { index, userScore ->
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        val color: Color = when (index) {
                            0 -> colorResource(id = R.color.gold)
                            1 -> colorResource(id = R.color.silver)
                            2 -> colorResource(id = R.color.bronze)
                            else -> colorResource(id = R.color.black)
                        }
                        Text(text = "${index + 1}. ${userScore.username}: ${userScore.score}",
                            modifier = Modifier.align(Alignment.Center),
                            style = TextStyle(fontSize = 28.sp),
                            color = color)
                    }

                }
            }
        }
    }
}