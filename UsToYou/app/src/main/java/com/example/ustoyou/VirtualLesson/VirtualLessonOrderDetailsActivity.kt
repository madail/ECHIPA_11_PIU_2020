package com.example.ustoyou.VirtualLesson

import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.ustoyou.CategoryActivity
import com.example.ustoyou.HarvestActivity
import com.example.ustoyou.MyOrdersActivity
import com.example.ustoyou.R

class VirtualLessonOrderDetailsActivity : AppCompatActivity() {
    private lateinit var virtualLessonDetailsAccessLessonLink: TextView
    private lateinit var virtualLessonDetailsTeacherName: TextView
    private lateinit var virtualLessonDetailsDate: TextView
    private lateinit var virtualLessonDetailsType: TextView
    private lateinit var virtualLessonDetailsPaymentType: TextView

    private lateinit var ratingLessonLayout: LinearLayout

    private lateinit var virtualLessonTeacherRating: RatingBar;
    private lateinit var virtualLessonContentRating: RatingBar;
    private lateinit var virtualLessonServiceRating: RatingBar;

    private lateinit var teacherName: String
    private lateinit var date: String
    private lateinit var type: String
    private lateinit var paymentType: String
    private var contentRating: Float = 0.0F
    private var teacherRating: Float = 0.0F
    private var serviceRating: Float = 0.0F
    private var lessonFinished: Boolean = false
    private var showLink: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual_lesson_order_details)

        supportActionBar?.title = "Teaching Service"

        bindItems()

        getExtras()

        initLayout()
    }

    private fun bindItems() {
        virtualLessonDetailsTeacherName  = findViewById(R.id.virtualLessonDetailsTeacherName)
        virtualLessonDetailsDate = findViewById(R.id.virtualLessonDetailsDate)
        virtualLessonDetailsType = findViewById(R.id.virtualLessonDetailsType)
        virtualLessonDetailsPaymentType  = findViewById(R.id.virtualLessonDetailsPaymentType)
        virtualLessonDetailsAccessLessonLink  = findViewById(R.id.virtualLessonDetailsAccessLessonLink)

        virtualLessonTeacherRating  = findViewById(R.id.providerRating)
        virtualLessonContentRating  = findViewById(R.id.contentRating)
        virtualLessonServiceRating  = findViewById(R.id.serviceRating)

        ratingLessonLayout = findViewById(R.id.virtualLessonDetailsRating)
    }

    private fun getExtras() {
        teacherName = intent.getStringExtra("teacherName").toString()
        if(teacherName.isEmpty()){
            teacherName = "Teacher name"
        }

        date = intent.getStringExtra("date").toString()
        if(teacherName.isEmpty()){
            date = "14/01/2021 14:30"
        }

        type = intent.getStringExtra("type").toString()
        if(type.isEmpty()){
            type = "Virtual"
        }

        paymentType = intent.getStringExtra("paymentType").toString()
        if(teacherName.isEmpty()){
            paymentType = "Credit Card"
        }

        contentRating = intent.getFloatExtra("contentRating", 0.0F)
        teacherRating = intent.getFloatExtra("teacherRating", 0.0F)
        serviceRating = intent.getFloatExtra("serviceRating", 0.0F)

        lessonFinished = intent.getBooleanExtra("lessonFinished", false)
        showLink = intent.getBooleanExtra("showLink", true)
    }

    private fun initLayout() {
        virtualLessonDetailsTeacherName.text = teacherName
        virtualLessonDetailsDate.text = date
        virtualLessonDetailsType.text = type
        virtualLessonDetailsPaymentType.text = paymentType

        virtualLessonTeacherRating.rating  = teacherRating
        virtualLessonContentRating.rating  = contentRating
        virtualLessonServiceRating.rating  = serviceRating

        if(lessonFinished) {
            ratingLessonLayout.visibility = View.VISIBLE
            virtualLessonDetailsAccessLessonLink.text = getString(R.string.virtual_link_expired)
            virtualLessonDetailsAccessLessonLink.setTextColor(Color.parseColor("#B00020"))

            virtualLessonTeacherRating.setIsIndicator(true)
            virtualLessonContentRating.setIsIndicator(true)
            virtualLessonServiceRating.setIsIndicator(true)
        } else {
            ratingLessonLayout.visibility = View.INVISIBLE
            virtualLessonDetailsAccessLessonLink.text = getString(R.string.access_virtual_lesson)
            virtualLessonDetailsAccessLessonLink.setTextColor(Color.parseColor("#00363a"))

            virtualLessonTeacherRating.setIsIndicator(false)
            virtualLessonContentRating.setIsIndicator(false)
            virtualLessonServiceRating.setIsIndicator(false)
        }

        if (!showLink) {
            virtualLessonDetailsAccessLessonLink.visibility = View.INVISIBLE
        }
    }

    fun handleOnLinkClick(view: View) {
        if(lessonFinished) {
            Toast.makeText(this@VirtualLessonOrderDetailsActivity, "This link has expired.", Toast.LENGTH_SHORT).show();
            return
        }

        Toast.makeText(this@VirtualLessonOrderDetailsActivity, "Opening lesson...", Toast.LENGTH_SHORT).show();

        val intent  = Intent(this, VirtualLessonActivity::class.java).apply {
            putExtra("teacherName", teacherName)
            putExtra("date", date)
            putExtra("type", type)
            putExtra("paymentType", paymentType)
            putExtra("contentRating", contentRating)
            putExtra("teacherRating", teacherRating)
            putExtra("serviceRating", serviceRating)
        }
        startActivity(intent)
    }

    override fun onBackPressed() {
        val intent  = Intent(this, CategoryActivity::class.java)
        startActivity(intent)
        finish()
    }
}