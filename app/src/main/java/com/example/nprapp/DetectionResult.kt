package com.example.nprapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "detection_results")
data class DetectionResult(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val confidence: Float
)
