package com.example.smartstudyhub

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import java.text.SimpleDateFormat
import java.util.*

class TaskPlannerActivity : AppCompatActivity() {

    private lateinit var taskPlanner: TaskPlanner
    private lateinit var taskListView: ListView
    private lateinit var addTaskButton: Button
    private lateinit var datePickerButton: Button
    private lateinit var taskInput: EditText
    private lateinit var selectedDateText: TextView
    private lateinit var noTasksText: TextView

    private var selectedDate: Date = Date()
    private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private var tasksAdapter: ArrayAdapter<String>? = null
    private var currentTasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_planner)

        taskPlanner = TaskPlanner(this)

        taskListView = findViewById(R.id.taskListView)
        addTaskButton = findViewById(R.id.addTaskButton)
        datePickerButton = findViewById(R.id.datePickerButton)
        taskInput = findViewById(R.id.taskInput)
        selectedDateText = findViewById(R.id.selectedDateText)
        noTasksText = findViewById(R.id.noTasksText)

        updateSelectedDate(selectedDate)

        addTaskButton.setOnClickListener {
            val taskDesc = taskInput.text.toString().trim()
            if (taskDesc.isNotEmpty()) {
                taskPlanner.addTask(selectedDate, taskDesc)
                taskInput.text.clear()
                loadTasksForSelectedDate()
                Toast.makeText(this, "Task added!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Enter a task description", Toast.LENGTH_SHORT).show()
            }
        }

        datePickerButton.setOnClickListener {
            showDatePicker()
        }

        taskListView.setOnItemClickListener { _, _, position, _ ->
            // Delete task on tap with confirmation
            val taskToDelete = currentTasks[position]
            val deleted = taskPlanner.deleteTask(selectedDate, taskToDelete.id)
            if (deleted) {
                Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show()
                loadTasksForSelectedDate()
            }
        }

        loadTasksForSelectedDate()
    }

    private fun updateSelectedDate(date: Date) {
        selectedDate = date
        selectedDateText.text = "Tasks for: ${sdf.format(date)}"
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        calendar.time = selectedDate
        val dpd = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val cal = Calendar.getInstance()
                cal.set(year, month, dayOfMonth)
                updateSelectedDate(cal.time)
                loadTasksForSelectedDate()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dpd.show()
    }

    private fun loadTasksForSelectedDate() {
        currentTasks = taskPlanner.getTasks(selectedDate).toMutableList()

        if (currentTasks.isEmpty()) {
            noTasksText.isVisible = true
            taskListView.isVisible = false
        } else {
            noTasksText.isVisible = false
            taskListView.isVisible = true

            val descriptions = currentTasks.map { it.description }
            tasksAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, descriptions)
            taskListView.adapter = tasksAdapter
        }
    }
}
