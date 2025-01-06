package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.databinding.ActivityStudentListBinding
import com.example.studentsapp.models.Student
import com.example.studentsapp.adapters.StudentAdapter
import com.example.studentsapp.repository.StudentRepository

class StudentListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentListBinding
    private val students = mutableListOf<Student>()
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StudentRepository.loadStudents(this)
        students.addAll(StudentRepository.getStudents())

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(
            students,
            onStudentClick = { student ->
                val intent = Intent(this, StudentDetailsActivity::class.java).apply {
                    putExtra("student", student)
                }
                startActivityForResult(intent, REQUEST_CODE_VIEW_DETAILS)  // שיחה עם StudentDetailsActivity
            },
            onCheckboxClick = { student, isChecked ->
                student.isChecked = isChecked
                StudentRepository.updateStudent(this, student)
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
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_VIEW_DETAILS -> {
                    val updatedStudent = data?.getSerializableExtra("updatedStudent") as? Student
                    updatedStudent?.let {
                        // עדכון הסטודנט ברשימה
                        val index = students.indexOfFirst { it.id == updatedStudent.id }
                        if (index != -1) {
                            students[index] = updatedStudent
                            StudentRepository.updateStudent(this, updatedStudent)
                            adapter.notifyItemChanged(index) // עדכון ה-RecyclerView
                        }
                    }
                }
                REQUEST_CODE_ADD_STUDENT -> {
                    val newStudent = data?.getSerializableExtra("newStudent") as? Student
                    newStudent?.let {
                        students.add(newStudent)
                        StudentRepository.addStudent(this, newStudent)
                        adapter.notifyItemInserted(students.size - 1)
                    }
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE_VIEW_DETAILS = 1
        const val REQUEST_CODE_ADD_STUDENT = 2
    }
}
