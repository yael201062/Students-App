import android.content.Context
import android.content.SharedPreferences
import com.example.studentsapp.models.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object StudentStorage {
    private const val PREF_NAME = "students_pref"
    private const val STUDENT_LIST_KEY = "student_list"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveStudents(context: Context, students: List<Student>) {
        val json = Gson().toJson(students)
        getSharedPreferences(context).edit().putString(STUDENT_LIST_KEY, json).apply()
    }

    fun loadStudents(context: Context): MutableList<Student> {
        val json = getSharedPreferences(context).getString(STUDENT_LIST_KEY, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<Student>>() {}.type
        return Gson().fromJson(json, type)
    }
}
