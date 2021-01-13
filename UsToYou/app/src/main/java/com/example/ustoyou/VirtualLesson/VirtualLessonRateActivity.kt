package com.example.ustoyou.VirtualLesson

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.R


class VirtualLessonRateActivity : AppCompatActivity() {
    private lateinit var virtualLessonTeacherRating: RatingBar
    private lateinit var virtualLessonContentRating: RatingBar
    private lateinit var virtualLessonServiceRating: RatingBar
    private lateinit var virtualLessonMessageTeacherInput: EditText

    private var teacherName: String = ""
    private var date: String = ""
    private var lessonType: String = ""
    private var paymentType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual_lesson_rate)

        supportActionBar?.title = "Rate your lesson"

        bindItems()

        getExtras()
    }

    private fun bindItems() {
        virtualLessonTeacherRating  = findViewById(R.id.providerRating)
        virtualLessonContentRating  = findViewById(R.id.contentRating)
        virtualLessonServiceRating  = findViewById(R.id.serviceRating)
        virtualLessonMessageTeacherInput  = findViewById(R.id.virtualLessonMessageTeacherInput)
    }

    private fun getExtras() {
        teacherName = intent.getStringExtra("teacherName").toString()
        date = intent.getStringExtra("date").toString()
        lessonType = intent.getStringExtra("lessonType").toString()
        paymentType = intent.getStringExtra("paymentType").toString()
    }

    fun onSaveRating(view: View) {
        onLeave()
    }

    private fun onLeave() {
        Toast.makeText(this@VirtualLessonRateActivity, virtualLessonMessageTeacherInput.text.toString(), Toast.LENGTH_SHORT)

        val intent  = Intent(this, VirtualLessonOrderDetailsActivity::class.java).apply {
            putExtra("teacherName", teacherName)
            putExtra("date", date)
            putExtra("lessonType", lessonType)
            putExtra("paymentType", paymentType)
            putExtra("contentRating", virtualLessonContentRating.rating)
            putExtra("teacherRating", virtualLessonTeacherRating.rating)
            putExtra("serviceRating", virtualLessonServiceRating.rating)
            putExtra("lessonFinished", true)
            putExtra("showLink", false)
        }
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        lateinit var dialog: AlertDialog

        val builder: AlertDialog.Builder = AlertDialog.Builder(this@VirtualLessonRateActivity)
        builder.setTitle("Confirmation")
        builder.setMessage("Are you sure you want to leave rating? You will not be able to return.")
        builder.setPositiveButton("Leave") { _: DialogInterface, _: Int ->
            onLeave()
        }
        builder.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        dialog = builder.create()
        dialog.show()
    }

}