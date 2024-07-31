package com.example.nprapp

import androidx.lifecycle.LiveData

class DetectionRepository(private val detectionResultDao: DetectionResultDao) {
    val allDetectionResults: LiveData<List<DetectionResult>> = detectionResultDao.getAllDetectionResults()

    suspend fun insert(detectionResult: DetectionResult) {
        val existingResult = detectionResultDao.getDetectionByNumber(detectionResult.text)
        if(existingResult==null){
            detectionResultDao.insert(detectionResult)
        }
    }

    suspend fun clearAll() {
        detectionResultDao.clearAll()
    }
}