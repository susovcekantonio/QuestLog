package com.example.questlog.network

import com.example.questlog.models.Game
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeToGameApi {
    @GET("games")
    suspend fun searchGames(@Query("platform") platform: String = "pc"): List<Game>
}