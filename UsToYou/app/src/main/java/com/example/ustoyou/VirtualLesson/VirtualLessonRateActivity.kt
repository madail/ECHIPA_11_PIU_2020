package com.example.ustoyou.VirtualLesson

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.R


class VirtualLessonRateActivity : AppCompatActivity() {
    private lateinit var virtualLessonTeacherRating: RatingBar;
    private lateinit var virtualLessonContentRating: RatingBar;
    private lateinit var virtualLessonServiceRating: RatingBar;
    private lateinit var virtualLessonMessageTeacherInput: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual_lesson_rate)

        virtualLessonTeacherRating  = findViewById(R.id.virtualLessonTeacherRating)
        virtualLessonContentRating  = findViewById(R.id.virtualLessonContentRating)
        virtualLessonServiceRating  = findViewById(R.id.virtualLessonServiceRating)
        virtualLessonMessageTeacherInput  = findViewById(R.id.virtualLessonMessageTeacherInput)

        supportActionBar?.title = "Rate your lesson"
    }

    fun onSaveRating(view: View) {


        Toast.makeText(this@VirtualLessonRateActivity, virtualLessonMessageTeacherInput.text.toString(), Toast.LENGTH_SHORT)
    }

}