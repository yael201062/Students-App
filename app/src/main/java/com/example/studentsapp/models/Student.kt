package com.example.studentsapp.models

import java.io.Serializable

data class Student(
    var id: String,
    var name: String,
    var isChecked: Boolean = false,
    val imageResId: Int
) : Serializable
