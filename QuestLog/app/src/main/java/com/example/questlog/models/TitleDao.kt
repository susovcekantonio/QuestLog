package com.example.questlog.models


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TitleDao {
    @Insert
    suspend fun insert(title: Title)

    @Query("SELECT * FROM titles")
    suspend fun getAllTitles(): List<Title>

    @Query("DELETE FROM titles WHERE id = :id")
    suspend fun deleteTitle(id: Int)
}