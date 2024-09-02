package com.example.questlog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questlog.models.Title
import com.example.questlog.repository.TitleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TitleViewModel(private val repository: TitleRepository) : ViewModel() {
    private val _titles = MutableStateFlow<List<Title>>(emptyList())
    val titles: StateFlow<List<Title>> = _titles

    init {
        loadTitles()
    }

    private fun loadTitles() {
        viewModelScope.launch {
            _titles.value = repository.getAllTitles()
        }
    }

    fun addTitle(title: Title) {
        viewModelScope.launch {
            repository.insert(title)
            loadTitles()
        }
    }

    fun deleteTitle(id: Int) {
        viewModelScope.launch {
            repository.deleteTitle(id)
            loadTitles()
        }
    }

    fun getRandomTitle(): Title? {
        val titlesList = _titles.value ?: return null
        return if (titlesList.isNotEmpty()) {
            titlesList.random()
        } else {
            null
        }
    }
}