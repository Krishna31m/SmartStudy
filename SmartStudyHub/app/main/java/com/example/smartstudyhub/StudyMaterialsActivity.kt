package com.example.smartstudyhub

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudyMaterialsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudyMaterialsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_materials)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.studyToolbar)
        setSupportActionBar(toolbar)
        // Enable the back button in the toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Study Materials"

        recyclerView = findViewById(R.id.studyMaterialsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val materialList = listOf(
            StudyMaterialCategory("Books"),
            StudyMaterialCategory("Notes"),
            StudyMaterialCategory("Question Papers"),
            StudyMaterialCategory("Other Materials")
        )

        adapter = StudyMaterialsAdapter(materialList)
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
