package com.example.questlog.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.questlog.R
import com.example.questlog.ui.theme.OpenSans

@Composable
fun HomeScreen(navController: NavController) {
    val backgroundImage = painterResource(id = R.drawable.joystick)
    BackgroundScreen(backgroundImage = backgroundImage) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            androidx.compose.material3.Text(
                text = "Quest Log",
                fontSize = 64.sp,
                fontFamily = OpenSans,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.navigate("game_search") }) {
                Text("Search Games")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.navigate("title_list") }) {
                Text("View Saved Titles")
            }
        }
    }
}