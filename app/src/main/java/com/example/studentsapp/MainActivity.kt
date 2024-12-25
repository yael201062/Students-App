package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.adapters.StudentAdapter
import com.example.studentsapp.repository.StudentRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup RecyclerView
        adapter = StudentAdapter(StudentRepository.getStudents()) { position ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Add sample data
        StudentRepository.addStudent(
            Student("1", "John Doe", false, R.drawable.student_pic)
        )
        StudentRepository.addStudent(
            Student("2", "Jane Smith", false, R.drawable.student_pic)
        )
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}
