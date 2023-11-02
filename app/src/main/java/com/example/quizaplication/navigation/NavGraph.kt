package com.example.quizaplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quizaplication.screens.HomeScreen

import com.example.quizaplication.screens.QuizThemeScreen
import com.example.quizaplication.screens.RandomTestScreen
import com.example.quizaplication.screens.SettingScreen
import com.example.quizaplication.screens.login.LoginScreen
import com.example.quizaplication.screens.login.LoginState
import com.example.quizaplication.screens.quiz.MultipleChoice
import com.example.quizaplication.screens.quiz.MultipleChoiceQuizScreen
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
       /* composable(
            route = Screen.MultipleChoiceQuiz.route
        ){
            MultipleChoiceQuizScreen(navController = navController)
        }*/
        composable(
            route = Screen.Random.route
        ){
            RandomTestScreen(navController = navController)
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
        composable(
            route = Screen.MultipleChoice.route
        ){backStackEntry ->
            val documentPath = backStackEntry.arguments?.getString("documentPath") ?: ""
            MultipleChoice(navController = navController, documentPath = documentPath)
        }

    }
}