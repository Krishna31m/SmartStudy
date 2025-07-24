package com.example.smartstudyhub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DashboardCourseAdapter(
    private val context: Context,
    private val courseList: List<Course>
) : RecyclerView.Adapter<DashboardCourseAdapter.DashboardViewHolder>() {

    inner class DashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseImage: ImageView = itemView.findViewById(R.id.courseImage)
        val courseTitle: TextView = itemView.findViewById(R.id.courseTitle)
        val courseDescription: TextView = itemView.findViewById(R.id.courseDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_dashboard_course, parent, false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val course = courseList[position]
        holder.courseImage.setImageResource(course.imageResId)
        holder.courseTitle.text = course.title
        holder.courseDescription.text = course.description

        holder.itemView.setOnClickListener {
            // Launch activity or handle item click
            Toast.makeText(context, "Clicked: ${course.title}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = courseList.size
}
