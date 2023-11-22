package com.example.quizaplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quizaplication.screens.home.HomeScreen

import com.example.quizaplication.screens.QuizThemeScreen
import com.example.quizaplication.screens.leaderboard.LeaderboardGlobalScreen
import com.example.quizaplication.screens.leaderboard.LeaderboardScreen
import com.example.quizaplication.screens.leaderboard.LeaderboardUserScreen
import com.example.quizaplication.screens.settings.SettingScreen
import com.example.quizaplication.screens.login.LoginScreen
import com.example.quizaplication.screens.quiz.SelectQuizTypeScreen
import com.example.quizaplication.screens.quiz.type.MultiMedia
import com.example.quizaplication.screens.quiz.type.MultipleChoice
import com.example.quizaplication.screens.quiz.type.TextInput
import com.example.quizaplication.screens.quiz.type.TrueOrFalse
import com.example.quizaplication.screens.signup.SignUpScreen


@Composable
fun SetUpNavGraph (navController : NavHostController){
    NavHost(navController = navController,
            startDestination = Screen.Home.route){
        composable(
                route = Screen.Home.route
        ){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.QuizTheme.route
        ){
            QuizThemeScreen(navController = navController)
        }
        composable(
            route = Screen.Leaderboard.route
        ) {
            LeaderboardScreen(navController = navController)
        }
        composable(route = Screen.LeaderboardUser.route) { backStackEntry ->
            val documentPath = backStackEntry.arguments?.getString("documentPath") ?: ""
            LeaderboardUserScreen(navController = navController, documentPath = documentPath)
        }
        composable(route = Screen.LeaderboardGlobal.route) { backStackEntry ->
            val documentPath = backStackEntry.arguments?.getString("documentPath") ?: ""
            val subjectPath = backStackEntry.arguments?.getString("subjectPath") ?: ""
            LeaderboardGlobalScreen(navController = navController, documentPath = documentPath, subjectPath = subjectPath)
        }
        composable(
            route = Screen.Setting.route
        ){
            SettingScreen(navController = navController)
        }
        composable(
            route = Screen.LogIn.route
        ){
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.Register.route
        ){
            SignUpScreen(navController = navController)
        }
        composable(route = Screen.SelectQuizType.route) { backStackEntry ->
            val documentPath = backStackEntry.arguments?.getString("documentPath") ?: ""
            SelectQuizTypeScreen(navController = navController, documentPath = documentPath)
        }

        composable(route = Screen.MultipleChoice.route) { backStackEntry ->
            val documentPath = backStackEntry.arguments?.getString("documentPath") ?: ""
            val difficulty = backStackEntry.arguments?.getString("difficulty")?: ""
            MultipleChoice(navController = navController, documentPath = documentPath, difficulty = difficulty)
        }
        composable(route = Screen.TextInput.route) { backStackEntry ->
            val documentPath = backStackEntry.arguments?.getString("documentPath") ?: ""
            val difficulty = backStackEntry.arguments?.getString("difficulty")?: ""
            TextInput(navController = navController, documentPath = documentPath, difficulty = difficulty)
        }

        composable(route = Screen.TrueOrFalse.route) { backStackEntry ->
            val documentPath = backStackEntry.arguments?.getString("documentPath") ?: ""
            val difficulty = backStackEntry.arguments?.getString("difficulty")?: ""
            TrueOrFalse(navController = navController, documentPath = documentPath, difficulty = difficulty)
        }
        composable(route = Screen.MultiMedia.route) { backStackEntry ->
            val documentPath = backStackEntry.arguments?.getString("documentPath") ?: ""
            val difficulty = backStackEntry.arguments?.getString("difficulty")?: ""
            MultiMedia(navController = navController, documentPath = documentPath, difficulty = difficulty)
        }

    }
}