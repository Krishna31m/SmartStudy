package com.example.smartstudyhub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CourseAdapter(
    private val context: Context,
    private val courseList: List<Course>

) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseTitle: TextView = itemView.findViewById(R.id.courseTitle)
        val courseDescription: TextView = itemView.findViewById(R.id.courseDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_item, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courseList[position]
        holder.courseTitle.text = course.title
        holder.courseDescription.text = course.description

        holder.itemView.setOnClickListener {
            when (course.title) {
                "Data Structures and Algorithms" -> context.startActivity(Intent(context, DataStructuresActivity::class.java))
                "Database Management Systems" -> context.startActivity(Intent(context, DBMSActivity::class.java))
                "Operating Systems" -> context.startActivity(Intent(context, OSActivity::class.java))
                "Computer Networks" -> context.startActivity(Intent(context, CNActivity::class.java))
                "Object-Oriented Programming (C++ / Java)" -> context.startActivity(Intent(context, OOPActivity::class.java))
                "Web Development (Frontend & Backend)" -> context.startActivity(Intent(context, WebDevActivity::class.java))
                "Software Engineering" -> context.startActivity(Intent(context, SEActivity::class.java))
                "Artificial Intelligence & Machine Learning" -> context.startActivity(Intent(context, AIMLActivity::class.java))
                "Data Science and Analytics" -> context.startActivity(Intent(context, DSActivity::class.java))
                "Mobile App Development (Android / iOS)" -> context.startActivity(Intent(context, MobileDevActivity::class.java))
                "Cloud Computing" -> context.startActivity(Intent(context, CloudActivity::class.java))
                "Cybersecurity Fundamentals" -> context.startActivity(Intent(context, CyberActivity::class.java))
                "Internet of Things (IoT)" -> context.startActivity(Intent(context, IoTActivity::class.java))
                "Blockchain Technology" -> context.startActivity(Intent(context, BlockchainActivity::class.java))
                "Human-Computer Interaction" -> context.startActivity(Intent(context, HCIActivity::class.java))
                "Compiler Design" -> context.startActivity(Intent(context, CompileIRActivityrActivity::class.java))
                "Distributed Systems" -> context.startActivity(Intent(context, DSysActivity::class.java))
                "Information Retrieval Systems" -> context.startActivity(Intent(context,IRActivity ::class.java))
                "DevOps & CI/CD" -> context.startActivity(Intent(context, DevOpsActivity::class.java))
                "NLP (Natural Language Processing)" -> context.startActivity(Intent(context, NLPActivity::class.java))
                else -> Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = courseList.size

}
