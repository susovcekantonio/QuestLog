package com.example.questlog

// AppModules.kt
import AuthViewModel
import org.koin.dsl.module
import com.example.questlog.models.TitleDao
import com.example.questlog.models.TitleDatabase
import com.example.questlog.repository.TitleRepository
import com.example.questlog.viewmodel.GameSearchViewModel
import com.example.questlog.viewmodel.TitleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single { TitleDatabase.getDatabase(get()).titleDao() }

    single { TitleRepository(get()) }

    viewModel { GameSearchViewModel(get()) }
    viewModel { TitleViewModel(get()) }
    viewModel { AuthViewModel() }
}
