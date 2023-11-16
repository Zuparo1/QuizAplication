package com.example.quizaplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quizaplication.screens.home.HomeScreen

import com.example.quizaplication.screens.QuizThemeScreen
import com.example.quizaplication.screens.SettingScreen
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
            MultipleChoice(navController = navController, documentPath = documentPath)
        }
        composable(route = Screen.TextInput.route) { backStackEntry ->
            val documentPath = backStackEntry.arguments?.getString("documentPath") ?: ""
            TextInput(navController = navController, documentPath = documentPath)
        }

        composable(route = Screen.TrueOrFalse.route) { navBackStackEntry ->
            val documentPath = navBackStackEntry.arguments?.getString("documentPath") ?: ""
            TrueOrFalse(navController = navController, documentPath = documentPath)
        }
        composable(route = Screen.MultiMedia.route) { navBackStackEntry ->
            val documentPath = navBackStackEntry.arguments?.getString("documentPath") ?: ""
            MultiMedia(navController = navController, documentPath = documentPath)
        }

    }
}