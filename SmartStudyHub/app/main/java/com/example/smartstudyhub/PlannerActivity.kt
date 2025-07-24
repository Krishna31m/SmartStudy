//package com.example.smartstudyhub
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//
//
//
//class PlannerActivity : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var editTextTask: EditText
//    private lateinit var addButton: Button
//    private lateinit var taskAdapter: TaskAdapter
//    private val taskList = mutableListOf<Task>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_planner)
//
//        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.plannerToolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//        recyclerView = findViewById(R.id.plannerRecyclerView)
//        editTextTask = findViewById(R.id.editTextTask)
//        addButton = findViewById(R.id.buttonAddTask)
//
//        taskAdapter = TaskAdapter(taskList)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = taskAdapter
//
//        addButton.setOnClickListener {
//            val taskText = editTextTask.text.toString()
//            if (taskText.isNotEmpty()) {
//                taskList.add(Task(taskText))
//                taskAdapter.notifyItemInserted(taskList.size - 1)
//                editTextTask.text.clear()
//            }
//        }
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val intent = Intent(this, DashboardActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(intent)
//        finish()
//        return true
//    }
//}
