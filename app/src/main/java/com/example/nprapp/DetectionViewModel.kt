package com.example.nprapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class DetectionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DetectionRepository
    val allDetectionResults: LiveData<List<DetectionResult>>

    init {
        val detectionResultDao = AppDatabase.getDatabase(application).detectionResultDao()
        repository = DetectionRepository(detectionResultDao)
        allDetectionResults = repository.allDetectionResults
    }

    fun insert(detectionResult: DetectionResult) = viewModelScope.launch {
        repository.insert(detectionResult)
    }

    fun clearAll() = viewModelScope.launch {
        repository.clearAll()
    }

}