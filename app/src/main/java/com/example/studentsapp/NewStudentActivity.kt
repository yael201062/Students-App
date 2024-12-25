package com.example.studentsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityNewStudentBinding
import com.example.studentsapp.models.Student
import com.example.studentsapp.repository.StudentRepository

class NewStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val id = binding.editId.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                StudentRepository.addStudent(
                    Student(
                        id = id,
                        name = name,
                        isChecked = false,
                        imageResId = R.drawable.student_pic_background
                    )
                )
                finish()
            }
        }

    }
}
