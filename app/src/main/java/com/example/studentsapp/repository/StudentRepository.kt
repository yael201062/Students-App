package com.example.studentsapp.repository

import android.content.Context
import com.example.studentsapp.models.Student

object StudentRepository {

    private val students = mutableListOf<Student>()

    fun loadStudents(context: Context) {
        students.clear()
        students.addAll(StudentStorage.loadStudents(context))
    }

    fun addStudent(context: Context, student: Student) {
        students.add(student)
        StudentStorage.saveStudents(context, students)
    }

    fun updateStudent(context: Context, updatedStudent: Student) {
        val index = students.indexOfFirst { it.id == updatedStudent.id }
        if (index != -1) {
            students[index] = updatedStudent
            StudentStorage.saveStudents(context, students)
        }
    }

    fun deleteStudent(context: Context, student: Student) {
        val iterator = students.iterator()
        while (iterator.hasNext()) {
            if (iterator.next().id == student.id) {
                iterator.remove()
                break
            }
        }
        StudentStorage.saveStudents(context, students)
    }


    // Get all students
    fun getStudents(): List<Student> {
        return students
    }
}
