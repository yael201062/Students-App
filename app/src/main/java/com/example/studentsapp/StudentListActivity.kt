package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.adapters.StudentAdapter
import com.example.studentsapp.databinding.ActivityStudentListBinding
import com.example.studentsapp.models.Student
import com.example.studentsapp.repository.StudentRepository

class StudentListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the RecyclerView
        val students = StudentRepository.getStudents() // Get the list of students
        val adapter = StudentAdapter(
            students,
            onStudentClick = { student ->
                // Open Student Details Activity
                val intent = Intent(this, StudentDetailsActivity::class.java).apply {
                    putExtra("student", student)
                }
                startActivity(intent)
            },
            onCheckboxClick = { student, isChecked ->
                // Update the check status of the student
                student.isChecked = isChecked
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Add a new student button
        binding.btnAddStudent.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }
}