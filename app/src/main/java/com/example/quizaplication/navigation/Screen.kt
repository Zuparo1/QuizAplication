package com.example.quizaplication.navigation

sealed class Screen(val route : String){
    object Home: Screen(route = "HomeScreen")
    object QuizTheme: Screen(route = "QuizThemeScreen")
    object Random: Screen(route = "RandomScreen")
    object Setting: Screen(route = "SettingScreen")
    object LogIn : Screen(route = "LoginScreen")
    object Register : Screen(route = "SignUpScreen")
    //object SelectQuizType : Screen(route = "SelectQuizTypeScreen/{documentPath}")
   // object MultipleChoice : Screen(route = "MultipleChoice/{documentPath}")

    object SelectQuizType : Screen(route = "SelectQuizTypeScreen/{documentPath}")
    object MultipleChoice : Screen(route = "MultipleChoice/{documentPath}")
    object Route : Screen(route = "{collectionPath}/{documentPath}")
    fun createRoute(collectionPath: String ,documentPath : String): String{
        return "$collectionPath/$documentPath"
        //return "MultipleChoice/$documentPath"
    }
    fun createQuizRoute (documentPath: String): String {
        return "SelectQuizTypeScreen/$documentPath"
    }
}
