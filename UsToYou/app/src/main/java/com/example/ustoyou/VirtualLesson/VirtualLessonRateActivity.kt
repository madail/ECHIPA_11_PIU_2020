package com.example.ustoyou.VirtualLesson

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
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

    private lateinit var teacherName: String
    private lateinit var date: String
    private lateinit var type: String
    private lateinit var paymentType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual_lesson_rate)

        virtualLessonTeacherRating  = findViewById(R.id.providerRating)
        virtualLessonContentRating  = findViewById(R.id.contentRating)
        virtualLessonServiceRating  = findViewById(R.id.serviceRating)
        virtualLessonMessageTeacherInput  = findViewById(R.id.virtualLessonMessageTeacherInput)

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
        type = intent.getStringExtra("type").toString()
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
            putExtra("type", type)
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
        super.onBackPressed()

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