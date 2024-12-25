
package com.example.studentsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.databinding.ActivityNewStudentBinding
import com.example.studentsapp.models.Student

class NewStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancel.setOnClickListener {
            finish() // Close the activity
        }

        binding.btnSave.setOnClickListener {
            // Gather the data from the input fields
            val name = binding.editName.text.toString()
            val id = binding.editId.text.toString()
            val phone = binding.editPhone.text.toString()
            val address = binding.editAddress.text.toString()
            val isActive = binding.checkActive.isChecked


            val newStudent = Student(name, id, false, R.drawable.student_pic_background) // Use the default image

            finish()
        }
    }
}
