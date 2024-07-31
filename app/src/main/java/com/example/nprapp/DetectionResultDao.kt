package com.example.nprapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DetectionResultDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(detectionResult: DetectionResult)

    @Query("SELECT * FROM detection_results")
    fun getAllDetectionResults(): LiveData<List<DetectionResult>>

    @Query("DELETE FROM detection_results")
    suspend fun clearAll()

    @Query("SELECT * FROM detection_results WHERE text = :text")
    suspend fun getDetectionByNumber(text: String): DetectionResult?
}