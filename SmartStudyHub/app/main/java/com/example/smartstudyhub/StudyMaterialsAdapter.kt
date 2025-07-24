package com.example.smartstudyhub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class StudyMaterialsAdapter(private val materialList: List<StudyMaterialCategory>) :
    RecyclerView.Adapter<StudyMaterialsAdapter.MaterialViewHolder>() {

    inner class MaterialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.materialTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.study_material_item, parent, false)
        return MaterialViewHolder(view)
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        val material = materialList[position]
        holder.title.text = material.title

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "${material.title} clicked", Toast.LENGTH_SHORT).show()
            // TODO: Add navigation to detailed activity for each material type
        }
    }

    override fun getItemCount(): Int = materialList.size
}
