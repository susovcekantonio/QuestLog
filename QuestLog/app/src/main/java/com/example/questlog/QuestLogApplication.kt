package com.example.questlog

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class QuestLogApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@QuestLogApplication)
            modules(appModule)
        }
    }
}