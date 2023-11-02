package com.example.quizaplication.navigation

sealed class Screen(val route : String){
    object Home: Screen(route = "HomeScreen")
    object QuizTheme: Screen(route = "QuizThemeScreen")
    object Random: Screen(route = "RandomScreen")
    object Setting: Screen(route = "SettingScreen")
   // object MultipleChoiceQuiz : Screen(route = "MultipleChoiceQuizScreen")

    object LogIn : Screen(route = "LoginScreen")
    object Register : Screen(route = "SignUpScreen")
    object MultipleChoice : Screen(route = "MultipleChoice/{documentPath}")
    fun createRoute(documentPath : String): String{
        return "MultipleChoice/$documentPath"
    }
}
