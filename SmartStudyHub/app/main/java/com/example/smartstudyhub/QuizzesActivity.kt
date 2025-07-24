package com.example.smartstudyhub

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuizzesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: QuizAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizzes)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.quizToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Course Quizzes"

        recyclerView = findViewById(R.id.quizzesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val quizList = listOf(
            Quiz("Computer Science", "DSA MCQ Quiz", "https://example.com/quiz1"),
            Quiz("Computer Science", "Operating Systems Quiz", "https://example.com/quiz2"),
            Quiz("Mechanical", "Thermodynamics Quiz", "https://example.com/quiz3"),
            Quiz("Electrical", "Circuit Theory Quiz", "https://example.com/quiz4")
        )

        adapter = QuizAdapter(quizList)
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
