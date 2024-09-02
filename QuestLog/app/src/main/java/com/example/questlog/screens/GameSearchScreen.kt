package com.example.questlog.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questlog.R
import com.example.questlog.models.Game
import com.example.questlog.models.Title
import com.example.questlog.viewmodel.GameSearchViewModel
import com.example.questlog.viewmodel.TitleViewModel
import kotlinx.coroutines.launch

@Composable
fun GameSearchScreen(
    gameSearchViewModel: GameSearchViewModel = viewModel(),
    titleViewModel: TitleViewModel = viewModel()
) {
    var query by remember { mutableStateOf("") }
    val games by gameSearchViewModel.games.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    fun searchGames(query: String) {
        isLoading = true
        coroutineScope.launch {
            gameSearchViewModel.searchGames(query)
            isLoading = false
        }
    }

    val backgroundImage = painterResource(id = R.drawable.joystick)
    BackgroundScreen(backgroundImage = backgroundImage) {

        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = query,
                    onValueChange = { query = it },
                    label = { Text("Search Games") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { searchGames(query) }) {
                    Text("Search")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn {
                    items(games) { game ->
                        GameItem(game, titleViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun GameItem(game: Game, titleViewModel: TitleViewModel) {
    val context = LocalContext.current

    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = game.title, style = MaterialTheme.typography.h6)
        Text(text = game.short_description, style = MaterialTheme.typography.body2)

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            titleViewModel.addTitle(
                Title(
                    title = game.title,
                    shortDescription = game.short_description
                )
            )
            Toast.makeText(context, "${game.title} saved", Toast.LENGTH_SHORT).show()
        }) {
            Text("Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameSearchScreenPreview() {
    GameSearchScreen()
}