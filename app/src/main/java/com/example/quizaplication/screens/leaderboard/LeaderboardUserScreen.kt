package com.example.quizaplication.screens.leaderboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizaplication.common.ext.lowerCaseFirstChar
import com.example.quizaplication.navigation.Screen
import com.example.quizaplication.navigation.TopNavBar
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LeaderboardUserScreen(
    navController: NavController,
    documentPath: String,
    viewModel: LeaderboardUserViewModel = hiltViewModel()
) {
    val firebaseAuth = FirebaseAuth.getInstance()
    val user = firebaseAuth.currentUser
    val leaderboardUserState by viewModel.leaderboardUserState

    val pageTitle = "Leaderboard"
    val quizSubjects = listOf(
        "History",
        "Math",
        "Science",
        "Programming"
    )

    LaunchedEffect(Unit) {
        if (user != null) {
            viewModel.getScore(user.uid, documentPath)
        }
    }

    fun getScore(subject: String):String {
        when (subject) {
            "History" -> return leaderboardUserState.history.toString()
            "Math" -> return leaderboardUserState.math.toString()
            "Science" -> return leaderboardUserState.science.toString()
            "Programming" -> return leaderboardUserState.programing.toString()
        }
        return ""
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopNavBar(navController,pageTitle)
        Text(text = "User",
            style = TextStyle(
                fontSize = 46.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            quizSubjects.forEach { quizSubject ->
                item {
                    Button(
                        onClick = {navController.navigate(route = Screen.LeaderboardGlobal.createLeaderboardGlobalRoute(documentPath, quizSubject.lowerCaseFirstChar()))},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)) {
                        Text(
                            text = "${quizSubject}: ${getScore(quizSubject)}",
                            style = TextStyle(fontSize = 18.sp),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}