package com.example.smartstudyhub

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var imgProfile: ImageView
    private lateinit var tvUserName: TextView
    private lateinit var toggle: ActionBarDrawerToggle

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val storageRef = FirebaseStorage.getInstance().reference
    private val IMAGE_PICK_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

//        imgProfile = findViewById(R.id.imgProfile)
//        tvUserName = findViewById(R.id.tvUserName)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)


        // Set user name and image in nav header
        val headerView: View = navView.getHeaderView(0)
        val imgProfile = headerView.findViewById<ImageView>(R.id.imgProfile)
        val tvUserName = headerView.findViewById<TextView>(R.id.tvUserName)



        val user = firebaseAuth.currentUser
        if (user != null) {
            tvUserName.text = user.email ?: "Guest"

            // Load image with Glide if available
            user.photoUrl?.let {
                Glide.with(this).load(it).into(imgProfile)
            }
        }

        imgProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }

        val dashboardCourses = listOf(
            Course(R.drawable.dsa_icon, "DSA", "Master data structures and algorithms"),
            Course(R.drawable.cpp_icon, "C++ Programming", "Learn the basics to advanced C++"),
            Course(R.drawable.dbms_icon, "DBMS", "Understand relational databases"),
            Course(R.drawable.se_icon, "Software Engineering", "Software dev process and models"),
            Course(R.drawable.cn_icon, "Computer Networks", "Learn about networking principles"),
            Course(R.drawable.os_icon, "Operating Systems", "Understand OS concepts")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.dashboardRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 columns
        recyclerView.adapter = DashboardCourseAdapter(this, dashboardCourses)


    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.nav_profile -> showToast("Profile clicked")
//            R.id.nav_course -> showToast("Course clicked")
//            R.id.nav_study_materials -> showToast("Study Materials clicked")
//            R.id.nav_video_lectures -> showToast("Video Lectures clicked")
//            R.id.nav_quizzes -> showToast("Quizzes clicked")
//            R.id.nav_planner -> showToast("Planner clicked")
//            R.id.nav_doubt_forum -> showToast("Doubt Forum clicked")
//            else -> showToast("Unknown option")
//        }
//        drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.nav_course -> {
                startActivity(Intent(this, CourseActivity::class.java))
            }
            R.id.nav_study_materials -> {
                startActivity(Intent(this, StudyMaterialsActivity::class.java))
            }
            R.id.nav_video_lectures -> {
                startActivity(Intent(this, VideoLecturesActivity::class.java))
            }
            R.id.nav_quizzes -> {
                startActivity(Intent(this, QuizzesActivity::class.java))
            }
            R.id.nav_planner -> {
                startActivity(Intent(this, TaskPlannerActivity::class.java))
            }
            R.id.nav_doubt_forum -> {
                startActivity(Intent(this, DoubtForumActivity::class.java))
            }
            else -> {
                Toast.makeText(this, "Unknown option", Toast.LENGTH_SHORT).show()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            imageUri?.let { uploadImageToFirebase(it) }
        }
    }

    private fun uploadImageToFirebase(uri: Uri) {
        val user = firebaseAuth.currentUser ?: return
        val imageRef = storageRef.child("profileImages/${user.uid}_${UUID.randomUUID()}.jpg")

        imageRef.putFile(uri)
            .addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setPhotoUri(downloadUri)
                        .build()

                    user.updateProfile(profileUpdates).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val headerView: View = navView.getHeaderView(0)
                            val imgProfile = headerView.findViewById<ImageView>(R.id.imgProfile)
                            Glide.with(this).load(downloadUri).into(imgProfile)
                            Toast.makeText(this, "Profile image updated!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Upload failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
