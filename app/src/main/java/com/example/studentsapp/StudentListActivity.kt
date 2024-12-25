
package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.NewStudentActivity
import com.example.studentsapp.R
import com.example.studentsapp.StudentDetailsActivity
import com.example.studentsapp.databinding.ActivityStudentListBinding
import com.example.studentsapp.models.Student
import com.example.studentsapp.adapters.StudentAdapter

class StudentListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentListBinding
    private val students = mutableListOf<Student>() // List to hold students

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView with a LinearLayoutManager
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter
        val adapter = StudentAdapter(
            students,
            onStudentClick = { student ->
                val intent = Intent(this, StudentDetailsActivity::class.java).apply {
                    putExtra("student", student) // Pass student object to details
                }
                startActivity(intent)
            },
            onCheckboxClick = { student, isChecked ->
                student.isChecked = isChecked
            }
        )
        binding.recyclerView.adapter = adapter

        binding.btnAddStudent.setOnClickListener {
            startActivity(Intent(this, NewStudentActivity::class.java))
        }


        loadStudents()
    }

    private fun loadStudents() {
        students.add(Student("John Doe", "S001",false, R.drawable.student_pic_background))
        students.add(Student("Jane Smith", "S002",false, R.drawable.student_pic_background))
        students.add(Student("Alice Johnson", "S003", false,R.drawable.student_pic_background))
        students.add(Student("Bob Brown", "S004",false, R.drawable.student_pic_background))
        (binding.recyclerView.adapter as StudentAdapter).notifyDataSetChanged()
    }
}
