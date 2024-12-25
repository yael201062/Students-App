package com.example.studentsapp.models

data class Student(
    var id: String,
    var name: String,
    var isChecked: Boolean = false,
    val imageResId: Int
)
