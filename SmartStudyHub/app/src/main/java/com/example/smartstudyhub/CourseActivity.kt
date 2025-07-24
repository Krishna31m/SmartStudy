package com.example.smartstudyhub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CourseActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        // Setup Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Courses"

        // Setup RecyclerView

        recyclerView = findViewById(R.id.recyclerViewCourses)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val courses = listOf(
            Course(1,"Data Structures and Algorithms", "Learn how to store, access, and manipulate data efficiently."),
            Course(2,"Database Management Systems", "Study relational databases, SQL, and data models."),
            Course(3,"Operating Systems", "Understand process management, memory, and file systems."),
            Course(4,"Computer Networks", "Explore protocols, OSI model, TCP/IP, and network security."),
            Course(5,"Object-Oriented Programming (C++ / Java)", "Design using objects, classes, inheritance, and polymorphism."),
            Course(6,"Web Development (Frontend & Backend)", "Build responsive websites using HTML, CSS, JS, and backend technologies."),
            Course(7,"Software Engineering", "Learn software development lifecycle, models, and documentation."),
            Course(8,"Artificial Intelligence & Machine Learning", "Understand AI concepts and machine learning algorithms."),
            Course(9,"Data Science and Analytics", "Use statistics, Python, and tools like Pandas & NumPy for insights."),
            Course(10,"Mobile App Development (Android / iOS)", "Create apps for Android or iOS using Java/Kotlin or Swift."),
            Course(11,"Cloud Computing", "Explore AWS, Azure, GCP and cloud-native app design."),
            Course(12,"Cybersecurity Fundamentals", "Learn encryption, threat modeling, and system hardening."),
            Course(13,"Internet of Things (IoT)", "Connect devices to the internet using sensors and microcontrollers."),
            Course(14,"Blockchain Technology", "Understand decentralized apps, smart contracts, and cryptocurrencies."),
            Course(15,"Human-Computer Interaction", "Design user-friendly interfaces with usability in mind."),
            Course(16,"Compiler Design", "Build lexical analyzers, parsers, and code generators."),
            Course(17,"Distributed Systems", "Study concurrency, fault tolerance, and system coordination."),
            Course(18,"Information Retrieval Systems", "Explore search engines, indexing, and query processing."),
            Course(19,"DevOps & CI/CD", "Integrate coding and deployment using Git, Jenkins, Docker."),
            Course(20,"NLP (Natural Language Processing)", "Work with text using tokenization, parsing, and transformers.")
        )

        courseAdapter = CourseAdapter(this, courses)
        recyclerView.adapter = courseAdapter

    }
}
