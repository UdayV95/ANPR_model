package com.example.nprapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DetectionResult::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun detectionResultDao(): DetectionResultDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "detection_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}