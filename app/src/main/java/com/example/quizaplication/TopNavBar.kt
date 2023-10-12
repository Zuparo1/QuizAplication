package com.example.quizaplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar() {
    Column {
        TopAppBar(
            title = {
                Text("Quiz-App")
            },
            navigationIcon = {
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }, actions = {
               /* IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.Share, null)
                }*/
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.Settings, null)
                }
            }
        )
    }
}