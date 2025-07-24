//package com.example.smartstudyhub
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//
//class TaskAdapter(private val taskList: List<Task>) :
//    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
//
//    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textViewTask: TextView = itemView.findViewById(R.id.textViewTask)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_task, parent, false)
//        return TaskViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
//        holder.textViewTask.text = taskList[position].title
//    }
//
//    override fun getItemCount(): Int = taskList.size
//}
