package com.example.studentsapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityEditStudentBinding
import com.example.studentsapp.models.Student

class EditStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // הגדרת Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // הצגת כפתור חזרה
        supportActionBar?.title = "Edit Student"  // כותרת הפעילות

        // הפיכת הכותרת ל-בולד
        val titleTextView = binding.toolbar.getChildAt(0) as? TextView
        titleTextView?.setTypeface(null, Typeface.BOLD)

        val student = intent.getSerializableExtra("student") as? Student

        student?.let {
            binding.editName.setText(it.name)
            binding.editId.setText(it.id)
            binding.editPhone.setText(it.phone)
            binding.editAddress.setText(it.address)
            binding.editChecked.isChecked = it.isChecked
        }

        binding.btnSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val id = binding.editId.text.toString()
            val phone = binding.editPhone.text.toString()
            val address = binding.editAddress.text.toString()
            val isChecked = binding.editChecked.isChecked

            val updatedStudent = student?.apply {
                this.name = name
                this.id = id
                this.phone = phone
                this.address = address
                this.isChecked = isChecked
            }

            updatedStudent?.let {
                val resultIntent = Intent()
                resultIntent.putExtra("updatedStudent", it)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        binding.btnDelete.setOnClickListener {
            student?.let {
                val resultIntent = Intent()
                resultIntent.putExtra("deletedStudent", it)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}