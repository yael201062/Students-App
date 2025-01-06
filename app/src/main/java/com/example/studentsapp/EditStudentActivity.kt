package com.example.studentsapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
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

        // הגדרת Toolbar ובלחצן אחורה
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // הצגת כפתור אחורה
        supportActionBar?.setTitle("Edit Student") // שם הפעילות

        // כותרת ב-bold
        val titleTextView = binding.toolbar.getChildAt(0) as TextView
        titleTextView.setTypeface(null, Typeface.BOLD)

        val student = intent.getSerializableExtra("student") as? Student

        student?.let {
            binding.editName.setText(it.name)
            binding.editId.setText(it.id.toString())
            binding.editPhone.setText(it.phone)
            binding.editAddress.setText(it.address)
            binding.editChecked.isChecked = it.isChecked
        }

        // שמירה של שינויים
        binding.btnSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val id = binding.editId.text.toString().toInt()
            val phone = binding.editPhone.text.toString()
            val address = binding.editAddress.text.toString()
            val isChecked = binding.editChecked.isChecked

            val updatedStudent = student?.apply {
                this.name = name
                this.id = id.toString()
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

    // פעולה להחזרת כפתור אחורה
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish() // סיום הפעילות ושיבה למסך הקודם
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
