package com.example.nprapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class DetectionResultAdapter : RecyclerView.Adapter<DetectionResultAdapter.DetectionViewHolder>(){

    private var results = emptyList<DetectionResult>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetectionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_detection_result, parent, false)
        return DetectionViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: DetectionViewHolder,
        position: Int
    ) {
        val current = results[position]
        val x=current.text.replace(" ","")
        holder.textView.text = "${x} "
        holder.textView1.text = "${current.confidence*100}"
    }

    override fun getItemCount()= results.size

    @SuppressLint("NotifyDataSetChanged")
    internal fun setResults(results: List<DetectionResult>) {
        this.results = results
        notifyDataSetChanged()
    }

    class DetectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val textView1: TextView = itemView.findViewById(R.id.textView1)
    }

}