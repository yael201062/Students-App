package com.example.studentsapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityEditStudentBinding
import com.example.studentsapp.models.Student
import com.example.studentsapp.repository.StudentRepository

class EditStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditStudentBinding
    private lateinit var student: Student // Assume Student is a data class containing name and ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the student data passed from the previous activity (assumed to be passed via Intent)
        student = intent.getSerializableExtra("student") as Student

        // Pre-fill the EditText fields with the current student's data
        binding.editName.setText(student.name)
        binding.editId.setText(student.id)

        // Save changes to the student
        binding.btnSave.setOnClickListener {
            val updatedName = binding.editName.text.toString()
            val updatedId = binding.editId.text.toString()

            if (updatedName.isEmpty() || updatedId.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Update the student (assuming StudentRepository is where we store data)
                student.name = updatedName
                student.id = updatedId
                StudentRepository.updateStudent(student)

                Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show()
                finish() // Close the activity
            }
        }

        // Delete the student
        binding.btnDelete.setOnClickListener {
            StudentRepository.deleteStudent(student)

            Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show()
            finish() // Close the activity
        }
    }
}