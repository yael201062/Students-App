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
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Student Details")
        val titleTextView = binding.toolbar.getChildAt(0) as TextView
        titleTextView.setTypeface(null, Typeface.BOLD)

        student = intent.getSerializableExtra("student") as? Student

        student?.let {
            binding.imageStudent.setImageResource(R.mipmap.student_pic_app)  // Set static image
            binding.textName.text = "Name: ${student?.name}"
            binding.textId.text = "ID: ${student?.id}"
            binding.textAddress.text = "Address: ${student?.address}"
            binding.textPhone.text = "Phone: ${student?.phone}"
            binding.textCheckedStatus.text = "Checked Status: ${if (student?.isChecked == true) "Checked" else "Not Checked"}"
        }

        binding.btnEdit.setOnClickListener {
            val editIntent = Intent(this, EditStudentActivity::class.java).apply {
                putExtra("student", student)
            }
            startActivityForResult(editIntent, REQUEST_CODE_EDIT_STUDENT)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_EDIT_STUDENT) {
                val updatedStudent = data?.getSerializableExtra("updatedStudent") as? Student
                updatedStudent?.let {
                    student = updatedStudent
                    binding.textName.text = "Name: ${updatedStudent.name}"
                    binding.textId.text = "ID: ${updatedStudent.id}"
                    binding.textPhone.text = "Phone: ${updatedStudent.phone}"
                    binding.textAddress.text = "Address: ${updatedStudent.address}"
                    binding.textCheckedStatus.text = "Checked Status: ${if (updatedStudent.isChecked) "Checked" else "Not Checked"}"

                    val resultIntent = Intent().apply {
                        putExtra("updatedStudent", updatedStudent)
                    }
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE_EDIT_STUDENT = 2
    }
}
