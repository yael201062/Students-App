package com.example.studentsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.models.Student

class StudentAdapter(
    private val students: List<Student>,
    private val onStudentClick: (Student) -> Unit,
    private val onCheckboxClick: (Student, Boolean) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    // ViewHolder class for a single row
    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentImage: ImageView = itemView.findViewById(R.id.studentImage)
        private val studentName: TextView = itemView.findViewById(R.id.studentName)
        private val studentId: TextView = itemView.findViewById(R.id.studentId)
        private val studentCheckbox: CheckBox = itemView.findViewById(R.id.studentCheckbox)

        fun bind(student: Student) {
            // Set the student details
            studentName.text = student.name
            studentId.text = "ID: ${student.id}"

            // Checkbox status
            studentCheckbox.isChecked = student.isChecked

            // Row click listener
            itemView.setOnClickListener {
                onStudentClick(student)
            }

            // Checkbox click listener
            studentCheckbox.setOnCheckedChangeListener { _, isChecked ->
                onCheckboxClick(student, isChecked)
            }
        }
    }

    // Inflates the row layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    // Binds data to the ViewHolder
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int {
        return students.size
    }
}