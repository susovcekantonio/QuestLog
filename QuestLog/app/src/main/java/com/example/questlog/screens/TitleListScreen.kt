package com.example.questlog.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.questlog.R
import com.example.questlog.ShakeDetector
import com.example.questlog.models.Title
import com.example.questlog.viewmodel.TitleViewModel
import kotlinx.coroutines.launch

@Composable
fun TitleListScreen(viewModel: TitleViewModel) {
    val backgroundImage = painterResource(id = R.drawable.joystick)
    BackgroundScreen(backgroundImage = backgroundImage) {

        val titles by viewModel.titles.collectAsState()
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()

        DisposableEffect(Unit) {
            val shakeDetector = ShakeDetector(context) {
                val selectedTitle = viewModel.getRandomTitle()
                selectedTitle?.let {
                    coroutineScope.launch {
                        Toast.makeText(context, "Selected Title: ${it.title}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            onDispose {
                shakeDetector.unregister()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (titles.isEmpty()) {
                // Display message if no saved games
                Text(
                    text = "No saved games available.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                titles.forEach { title ->
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Text(
                            text = "Title: ${title.title}",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = "Description: ${title.shortDescription}",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = {
                            viewModel.deleteTitle(title.id)

                            Toast.makeText(context, "${title.title} deleted", Toast.LENGTH_SHORT)
                                .show()
                        }) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}