package com.example.questlog.models


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Title::class], version = 1, exportSchema = false)
abstract class TitleDatabase : RoomDatabase() {
    abstract fun titleDao(): TitleDao

    companion object {
        @Volatile
        private var INSTANCE: TitleDatabase? = null

        fun getDatabase(context: Context): TitleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TitleDatabase::class.java,
                    "title_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}