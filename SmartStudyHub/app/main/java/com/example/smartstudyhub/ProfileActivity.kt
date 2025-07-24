package com.example.smartstudyhub

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val imgProfile = findViewById<ImageView>(R.id.imgProfile)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)

        val user = firebaseAuth.currentUser
        user?.let {
            tvName.text = it.displayName ?: "No Name"
            tvEmail.text = it.email ?: "No Email"
            it.photoUrl?.let { uri ->
                Glide.with(this).load(uri).into(imgProfile)
            }
        }
    }
}
