package com.example.ustoyou.ScheduleTherapySession

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ustoyou.ConfirmationActivity
import com.example.ustoyou.R
import com.example.ustoyou.model.Order
import com.example.ustoyou.model.TherapyOrder
import com.example.ustoyou.teaching.TeachingServiceActivity

class ScheduleTherapyConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_therapy_confirmation)
    }

    fun onBack(view: View) {
        val intent = Intent(this, ScheduleTherapyDetailsActivity::class.java)
        startActivity(intent)
    }

    fun onConfirm(view: View) {
        val intent = Intent(this, ConfirmationActivity::class.java)
        intent.putExtra("order", Order("Therapy", "", R.drawable.therapy_service_1))
        startActivity(intent)
    }
}