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

        // Set up the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Student Details")
        val titleTextView = binding.toolbar.getChildAt(0) as TextView
        titleTextView.setTypeface(null, Typeface.BOLD)

        // Receive student details from intent
        val student = intent.getSerializableExtra("student") as? Student

        if (student != null) {
            binding.textName.text = student.name
            binding.textId.text = student.id
            binding.textAddress.text = student.address
            binding.textPhone.text = student.phone
            binding.textCheckedStatus.text = if (student.isChecked) "Checked" else "Not Checked"
        }

        // Edit button to navigate to EditStudentActivity
        binding.btnEdit.setOnClickListener {
            val editIntent = Intent(this, EditStudentActivity::class.java).apply {
                putExtra("student", student)
            }
            startActivity(editIntent)
        }
    }

    // Handle back button in the toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish() // Go back to the previous screen
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
