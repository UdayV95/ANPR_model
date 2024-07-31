package com.example.nprapp


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nprapp.databinding.ActivityDetectionBinding


class Detection : AppCompatActivity(), Detector.DetectorListener {
    private lateinit var adapter: DetectionResultAdapter
    private lateinit var binding: ActivityDetectionBinding
    private val detectionViewModel: DetectionViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main1)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityDetectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DetectionResultAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        detectionViewModel.allDetectionResults.observe(this, Observer { results ->
            results?.let { adapter.setResults(it) }
        })

        binding.buttonReset.setOnClickListener {
            detectionViewModel.clearAll()
        }
    }

    override fun onEmptyDetect() {
        binding.recyclerView.invalidate()
    }

    override fun onDetect(boundingBoxes: List<BoundingBox>, inferenceTime: Long) {
        TODO("Not yet implemented")
    }

    override fun onDetect(detectionResults: List<DetectionResult>) {
        detectionResults.forEach { detectionResult ->
            detectionViewModel.insert(detectionResult)
        }
    }
}