package com.example.smartstudyhub

import android.content.Context
import android.content.SharedPreferences
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

// Data class representing a single Task with unique ID and description
data class Task(
    val id: String = UUID.randomUUID().toString(),
    val description: String
)

class TaskPlanner(private val context: Context) {

    private val PREFS_NAME = "task_planner_prefs"
    private val TASKS_KEY = "tasks_key"
    private val gson = Gson()
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // Stores tasks mapped by date string "yyyy-MM-dd" -> List of Tasks
    private var tasksMap: MutableMap<String, MutableList<Task>> = mutableMapOf()

    init {
        loadTasks()
    }

    // Load tasks from SharedPreferences (JSON)
    private fun loadTasks() {
        val json = prefs.getString(TASKS_KEY, null)
        if (json != null) {
            val type = object : TypeToken<MutableMap<String, MutableList<Task>>>() {}.type
            tasksMap = gson.fromJson(json, type)
        }
    }

    // Save current tasksMap to SharedPreferences
    private fun saveTasks() {
        val editor = prefs.edit()
        val json = gson.toJson(tasksMap)
        editor.putString(TASKS_KEY, json)
        editor.apply()
    }

    // Format Date to "yyyy-MM-dd" string
    private fun formatDate(date: Date): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(date)
    }

    /**
     * Add a new task for a given date.
     * @param date Date for the task
     * @param description Task description
     */
    fun addTask(date: Date, description: String) {
        val dateKey = formatDate(date)
        val list = tasksMap.getOrPut(dateKey) { mutableListOf() }
        list.add(Task(description = description))
        saveTasks()
    }

    /**
     * Get list of tasks for a given date.
     * @param date Date to get tasks for
     * @return List of tasks (empty if none)
     */
    fun getTasks(date: Date): List<Task> {
        val dateKey = formatDate(date)
        return tasksMap[dateKey]?.toList() ?: emptyList()
    }

    /**
     * Delete a task by ID for a given date.
     * @param date Date of the task
     * @param taskId Task ID to remove
     * @return true if deleted, false if not found
     */
    fun deleteTask(date: Date, taskId: String): Boolean {
        val dateKey = formatDate(date)
        val tasks = tasksMap[dateKey] ?: return false
        val removed = tasks.removeIf { it.id == taskId }
        if (tasks.isEmpty()) {
            tasksMap.remove(dateKey)
        }
        if (removed) {
            saveTasks()
        }
        return removed
    }

    /**
     * Delete all tasks for a given date.
     * @param date Date to clear tasks for
     */
    fun deleteAllTasksForDate(date: Date) {
        val dateKey = formatDate(date)
        if (tasksMap.containsKey(dateKey)) {
            tasksMap.remove(dateKey)
            saveTasks()
        }
    }
}
