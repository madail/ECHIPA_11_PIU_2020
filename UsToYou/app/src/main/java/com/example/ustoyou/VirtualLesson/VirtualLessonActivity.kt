package com.example.ustoyou.VirtualLesson

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ustoyou.R

class VirtualLessonActivity : AppCompatActivity() {
    private lateinit var teacherName: String
    private lateinit var date: String
    private lateinit var type: String
    private lateinit var paymentType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual_lesson)

        supportActionBar?.title = "Lesson"

        getExtras()
    }

    private fun getExtras() {
        teacherName = intent.getStringExtra("teacher_name").toString()
        if(teacherName.isEmpty()){
            teacherName = "Teacher name"
        }

        date = intent.getStringExtra("date").toString()
        if(teacherName.isEmpty()){
            date = "14/01/2021 14:30"
        }

        type = intent.getStringExtra("type").toString()
        if(teacherName.isEmpty()){
            type = "Virtual"
        }

        paymentType = intent.getStringExtra("paymentType").toString()
        if(teacherName.isEmpty()){
            paymentType = "Credit Card"
        }
    }

    fun toggleWebBetweenTeacherAndStudent(view: View) {
        Toast.makeText(this@VirtualLessonActivity, "Switching to your camera...", Toast.LENGTH_SHORT).show()
    }

    fun toggleStudentCamera(view: View) {
        Toast.makeText(this@VirtualLessonActivity, "Turning off to your camera...", Toast.LENGTH_SHORT).show()
    }

    fun toggleStudentMicrophone(view: View) {
        Toast.makeText(this@VirtualLessonActivity, "Muting your microphone...", Toast.LENGTH_SHORT).show()
    }

    fun sendMessage(view: View) {
        Toast.makeText(this@VirtualLessonActivity, "Sending your message...", Toast.LENGTH_SHORT).show()
    }

    fun leaveLesson(view: View) {
        onLeave()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        lateinit var dialog: AlertDialog

        val builder: AlertDialog.Builder = AlertDialog.Builder(this@VirtualLessonActivity)
        builder.setTitle("Confirmation")
        builder.setMessage("Are you sure you want to end the lesson? You will not be able to return.")
        builder.setPositiveButton("End lesson") { _: DialogInterface, _: Int ->
            onLeave()
        }
        builder.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        dialog = builder.create()
        dialog.show()
    }

    private fun onLeave() {
        Toast.makeText(this@VirtualLessonActivity, "Leaving lesson...", Toast.LENGTH_SHORT).show()
        val intent  = Intent(this, VirtualLessonRateActivity::class.java).apply {
            putExtra("teacherName", teacherName)
            putExtra("date", date)
            putExtra("type", type)
            putExtra("paymentType", paymentType)
            putExtra("lessonFinished", true)
        }
        startActivity(intent)
        finish()
    }
}