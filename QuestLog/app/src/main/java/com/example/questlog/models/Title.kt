package com.example.questlog.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "titles")
data class Title(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val shortDescription: String
)
