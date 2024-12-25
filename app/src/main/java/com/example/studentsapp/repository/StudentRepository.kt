package com.example.studentsapp.repository

import com.example.studentsapp.models.Student
import com.example.studentsapp.R

object StudentRepository {
    private val students = mutableListOf<Student>()

    fun getStudents(): List<Student> = students

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(index: Int, student: Student) {
        students[index] = student
    }

    fun deleteStudent(index: Int) {
        students.removeAt(index)
    }
}