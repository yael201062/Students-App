package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityStudentDetailsBinding
import com.example.studentsapp.models.Student

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the student object passed from the intent
        val student = intent.getSerializableExtra("student") as? Student

        if (student != null) {
            // Set the student details
            binding.textName.text = student.name
            binding.textId.text = student.id
        }

        // Edit button to navigate to EditStudentActivity
        binding.btnEdit.setOnClickListener {
            val editIntent = Intent(this, EditStudentActivity::class.java).apply {
                putExtra("student", student) // Pass the student to EditStudentActivity
            }
            startActivity(editIntent)
        }
    }
}