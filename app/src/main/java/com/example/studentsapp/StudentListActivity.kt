package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.databinding.ActivityStudentListBinding
import com.example.studentsapp.models.Student
import com.example.studentsapp.adapters.StudentAdapter

class StudentListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentListBinding
    private val students = mutableListOf<Student>()
    private lateinit var adapter: StudentAdapter

    companion object {
        const val REQUEST_CODE_ADD_STUDENT = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        students.addAll(StudentStorage.loadStudents(this))
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentAdapter(
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
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_STUDENT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_STUDENT && resultCode == RESULT_OK) {
            val newStudent = data?.getSerializableExtra("newStudent") as? Student
            if (newStudent != null) {
                students.add(newStudent)
                adapter.notifyDataSetChanged()
                StudentStorage.saveStudents(this, students)
            }
        }
    }
}
