package com.example.ustoyou.AccesVirtualLesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.ustoyou.R

class VirtualLessonOrderDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual_lesson_order_details)

        supportActionBar?.title = "Teaching Service"
        val virtualLessonDetailsAccessLessonLink : TextView = findViewById(R.id.virtualLessonDetailsAccessLessonLink)
        virtualLessonDetailsAccessLessonLink.setOnClickListener {
            Toast.makeText(this@VirtualLessonOrderDetails, "Opening lesson...", Toast.LENGTH_SHORT).show();
        }
    }
}