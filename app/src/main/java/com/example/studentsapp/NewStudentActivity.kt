package com.example.studentsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.models.Student
import com.example.studentsapp.repository.StudentRepository
import kotlinx.android.synthetic.main.activity_new_student.*

class NewStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        btnSave.setOnClickListener {
            val name = editName.text.toString()
            val id = editId.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                StudentRepository.addStudent(Student(id, name, false, R.drawable.student_pic_background))
                finish()
            }
        }
    }
}