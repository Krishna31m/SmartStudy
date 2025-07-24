package com.example.smartstudyhub

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VideoLectureAdapter(private val lectureList: List<VideoLecture>) :
    RecyclerView.Adapter<VideoLectureAdapter.VideoViewHolder>() {

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseName: TextView = itemView.findViewById(R.id.courseName)
        val videoTitle: TextView = itemView.findViewById(R.id.videoTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video_lecture, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val lecture = lectureList[position]
        holder.courseName.text = lecture.courseName
        holder.videoTitle.text = lecture.videoTitle

        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(lecture.videoUrl))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = lectureList.size
}
