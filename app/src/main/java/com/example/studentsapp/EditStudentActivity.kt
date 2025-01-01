package com.example.studentsapp

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.databinding.ActivityEditStudentBinding
import com.example.studentsapp.models.Student
import com.example.studentsapp.repository.StudentRepository

class EditStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditStudentBinding
    private lateinit var student: Student

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Edit Student"
        val titleTextView = binding.toolbar.getChildAt(0) as TextView
        titleTextView.setTypeface(null, Typeface.BOLD)

        student = intent.getSerializableExtra("student") as Student
        binding.imageStudent.setImageResource(R.mipmap.student_pic_app)  // Set static image
        binding.editName.setText(student.name)
        binding.editId.setText(student.id)
        binding.editAddress.setText(student.address)
        binding.editPhone.setText(student.phone)
        binding.editChecked.isChecked = student.isChecked

        binding.btnSave.setOnClickListener {
            val updatedName = binding.editName.text.toString()
            val updatedId = binding.editId.text.toString()
            val updatedAddress = binding.editAddress.text.toString()
            val updatedPhone = binding.editPhone.text.toString()
            val updatedChecked = binding.editChecked.isChecked

            if (updatedName.isNotEmpty() && updatedId.isNotEmpty()) {
                student.name = updatedName
                student.id = updatedId
                student.address = updatedAddress
                student.phone = updatedPhone
                student.isChecked = updatedChecked

                StudentRepository.updateStudent(student)
                Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }


        binding.btnDelete.setOnClickListener {
            StudentRepository.deleteStudent(student)
            Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}