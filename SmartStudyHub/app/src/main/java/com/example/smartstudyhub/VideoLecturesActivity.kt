package com.example.smartstudyhub

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VideoLecturesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VideoLectureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_lectures)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.videoToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Video Lectures"

        recyclerView = findViewById(R.id.videoRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val videoLectures = listOf(
            VideoLecture("Computer Science", "Intro to Java", "https://www.youtube.com/watch?v=GoXwIVyNvX0"),
            VideoLecture("Computer Science", "Data Structures", "https://www.youtube.com/watch?v=RBSGKlAvoiM"),
            VideoLecture("Electrical", "Basic Circuit Theory", "https://www.youtube.com/watch?v=8y2y-QFf7kY"),
            VideoLecture("Mechanical", "Thermodynamics Basics", "https://www.youtube.com/watch?v=bgaZ5P6jHj8")
        )

        adapter = VideoLectureAdapter(videoLectures)
        recyclerView.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
        return true
    }
}
