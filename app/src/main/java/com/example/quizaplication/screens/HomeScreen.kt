package com.example.quizaplication.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quizaplication.R
import com.example.quizaplication.navigation.Screen
import com.example.quizaplication.navigation.SetUpNavGraph

@Composable
fun HomeScreen(navController: NavController) {
    val logoDrawable = painterResource(id = R.drawable.ic_launcher_foreground)
    val backgorundDrawable = painterResource(id = R.drawable.ic_launcher_background)

    Box(
            modifier = Modifier.fillMaxSize()
    ){
        Image(painter = backgorundDrawable,
                contentDescription ="Background picture for home-screen",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
        )
        Column (
                modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                    painter = logoDrawable,
                    contentDescription = "Application Logo",
                    modifier = Modifier.size(120.dp))
            Text(
                    text = "Welcome to the Quiz",
                    style = TextStyle(
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column (
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally

            ){
                Button(
                        onClick = { navController.navigate(route = Screen.QuizTheme.route) },
                        modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(0.5f)
                ) {
                    Text(text = "Start Quizzing")
                }
                Button(
                        onClick = { navController.navigate(route = Screen.LogIn.route) },
                        modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(0.5f)
                ) {
                    Text(text = "Log In")
                }
                Button(
                        onClick = { navController.navigate(route = Screen.Register.route) },
                        modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(0.5f)
                ) {
                    Text(text = "Register")
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview(){
    lateinit var navController: NavHostController
    navController = rememberNavController()
    SetUpNavGraph(navController = navController)
}
