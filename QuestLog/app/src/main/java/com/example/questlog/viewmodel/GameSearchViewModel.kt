package com.example.questlog.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questlog.models.Game
import com.example.questlog.models.Title
import com.example.questlog.network.FreeToGameApi
import com.example.questlog.repository.TitleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameSearchViewModel(private val repository: TitleRepository) : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.freetogame.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(FreeToGameApi::class.java)

    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> = _games

    fun searchGames(query: String) {
        viewModelScope.launch {
            try {
                val allGames = api.searchGames()
                _games.value = allGames.filter { it.title.contains(query, ignoreCase = true) }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun addTitle(title: Title) {
        viewModelScope.launch {
            repository.insert(title)
        }
    }
}