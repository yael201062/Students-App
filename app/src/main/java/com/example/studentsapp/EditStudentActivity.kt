package com.example.studentsapp

import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityEditStudentBinding
import com.example.studentsapp.models.Student
import com.example.studentsapp.repository.StudentRepository

class EditStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditStudentBinding
    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // הגדרת toolbar עם כותרת מותאמת
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Edit Student"
        val titleTextView = binding.toolbar.getChildAt(0) as TextView
        titleTextView.setTypeface(null, Typeface.BOLD)

        // קבלת פרטי הסטודנט שנשלחו
        student = intent.getSerializableExtra("student") as Student

        // Pre-fill the EditText fields with the current student's data
        binding.editName.setText(student.name)
        binding.editId.setText(student.id)
        binding.editAddress.setText(student.address)
        binding.editPhone.setText(student.phone)
        binding.editChecked.isChecked = student.isChecked

        // כפתור שמירה
        binding.btnSave.setOnClickListener {
            val updatedName = binding.editName.text.toString()
            val updatedId = binding.editId.text.toString()
            val updatedAddress = binding.editAddress.text.toString()
            val updatedPhone = binding.editPhone.text.toString()
            val updatedChecked = binding.editChecked.isChecked

            if (updatedName.isNotEmpty() && updatedId.isNotEmpty()) {
                // Update the student (assuming StudentRepository is where we store data)
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

        // Delete the student
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
