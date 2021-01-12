package com.example.ustoyou.VirtualLesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.ustoyou.R

class VirtualLessonActivity : AppCompatActivity() {
    private lateinit var virtualLessonStudentSwitchWeb: ImageView;
    private lateinit var virtualLessonStudentWebCamIcon: ImageView;
    private lateinit var virtualLessonMuteUnmuteIcon: ImageView;
    private lateinit var virtualLessonChatSubmitButton: ImageView;
    private lateinit var virtualLessonDetailsLeaveLessonLink: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual_lesson)

        virtualLessonStudentSwitchWeb  = findViewById(R.id.virtualLessonStudentSwitchWeb)
        virtualLessonStudentSwitchWeb.setOnClickListener {
            Toast.makeText(this@VirtualLessonActivity, "Switching to your camera...", Toast.LENGTH_SHORT).show()
        }

        virtualLessonStudentWebCamIcon  = findViewById(R.id.virtualLessonStudentWebCamIcon)
        virtualLessonStudentWebCamIcon.setOnClickListener {
            Toast.makeText(this@VirtualLessonActivity, "Turning off to your camera...", Toast.LENGTH_SHORT).show()
        }

        virtualLessonMuteUnmuteIcon  = findViewById(R.id.virtualLessonMuteUnmuteIcon)
        virtualLessonMuteUnmuteIcon.setOnClickListener {
            Toast.makeText(this@VirtualLessonActivity, "Muting your microphone...", Toast.LENGTH_SHORT).show()
        }

        virtualLessonChatSubmitButton  = findViewById(R.id.virtualLessonChatSubmitButton)
        virtualLessonChatSubmitButton.setOnClickListener {
            Toast.makeText(this@VirtualLessonActivity, "Sending your message...", Toast.LENGTH_SHORT).show()
        }

        virtualLessonDetailsLeaveLessonLink  = findViewById(R.id.virtualLessonDetailsLeaveLessonLink)
        virtualLessonDetailsLeaveLessonLink.setOnClickListener {
            Toast.makeText(this@VirtualLessonActivity, "Leaving lesson...", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}