package com.example.questlog.repository

import androidx.annotation.WorkerThread
import com.example.questlog.models.Title
import com.example.questlog.models.TitleDao

class TitleRepository(private val titleDao: TitleDao) {
    suspend fun insert(title: Title) {
        titleDao.insert(title)
    }

    suspend fun getAllTitles(): List<Title> {
        return titleDao.getAllTitles()
    }

    suspend fun deleteTitle(id: Int) {
        titleDao.deleteTitle(id)
    }
}