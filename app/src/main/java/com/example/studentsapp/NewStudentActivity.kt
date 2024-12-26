
package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.databinding.ActivityNewStudentBinding
import com.example.studentsapp.models.Student

class NewStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val id = binding.editId.text.toString()
            val phone = binding.editPhone.text.toString()
            val address = binding.editAddress.text.toString()
            val isActive = binding.checkActive.isChecked

            if (name.isNotEmpty() && id.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty()) {
                val newStudent = Student(
                    id = id,
                    name = name,
                    address = address,
                    phone = phone,
                    isChecked = isActive,
                    imageResId = R.drawable.student_pic_background
                )

                val resultIntent = Intent().apply {
                    putExtra("newStudent", newStudent)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
