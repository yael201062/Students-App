package com.example.studentsapp.repository

import com.example.studentsapp.models.Student

object StudentRepository {

    private val students = mutableListOf<Student>()

    // Add a student to the list (used when adding a new student)
    fun addStudent(student: Student) {
        students.add(student)
    }

    // Update a student's details (used when editing a student)
    fun updateStudent(updatedStudent: Student) {
        val index = students.indexOfFirst { it.id == updatedStudent.id }
        if (index != -1) {
            students[index] = updatedStudent
        }
    }

    // Delete a student from the list
    fun deleteStudent(student: Student) {
        students.removeIf { it.id == student.id }
    }

    // Get all students
    fun getStudents(): List<Student> {
        return students
    }
}