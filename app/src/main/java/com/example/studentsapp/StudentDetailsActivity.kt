package com.example.studentsapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityStudentDetailsBinding
import com.example.studentsapp.models.Student

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Student Details")
        val titleTextView = binding.toolbar.getChildAt(0) as TextView
        titleTextView.setTypeface(null, Typeface.BOLD)


        val student = intent.getSerializableExtra("student") as? Student

        if (student != null) {
            binding.imageStudent.setImageResource(R.mipmap.student_pic_app)  // Set static image
            binding.textName.text = "Name: ${student.name}"
            binding.textId.text = "ID: ${student.id}"
            binding.textAddress.text = "Address: ${student.address}"
            binding.textPhone.text = "Phone: ${student.phone}"
            binding.textCheckedStatus.text = "Checked Status: ${if (student.isChecked) "Checked" else "Not Checked"}"
        }

        binding.btnEdit.setOnClickListener {
            val editIntent = Intent(this, EditStudentActivity::class.java).apply {
                putExtra("student", student)
            }
            startActivity(editIntent)
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