package com.example.quizaplication.navigation

sealed class Screen(val route : String){
    object Home: Screen(route = "HomeScreen")
    object QuizTheme: Screen(route = "QuizThemeScreen")
    object Random: Screen(route = "RandomScreen")
    object Setting: Screen(route = "SettingScreen")
    object MultipleChoiceQuiz : Screen(route = "MultipleChoiceQuizScreen")
}
