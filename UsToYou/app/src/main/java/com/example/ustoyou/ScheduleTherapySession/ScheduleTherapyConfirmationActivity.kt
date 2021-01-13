package com.example.ustoyou.ScheduleTherapySession

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import com.example.ustoyou.ConfirmationActivity
import com.example.ustoyou.R
import com.example.ustoyou.model.Order
import com.example.ustoyou.model.TherapyOrder
import com.example.ustoyou.teaching.TeachingServiceActivity

class ScheduleTherapyConfirmationActivity : AppCompatActivity() {
    private lateinit var scheduleTherapyUserName: EditText
    private lateinit var scheduleTherapyUserPhone: EditText
    private lateinit var scheduleTherapyUserAddress: EditText
    private lateinit var scheduleTherapyType: EditText
    private lateinit var scheduleTherapyDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_therapy_confirmation)

        bindItems()
        initLayout()
    }

    private fun bindItems() {
        scheduleTherapyUserName = findViewById(R.id.scheduleTherapyUserName)
        scheduleTherapyUserPhone = findViewById(R.id.scheduleTherapyUserPhone)
        scheduleTherapyUserAddress = findViewById(R.id.scheduleTherapyUserAddress)
        scheduleTherapyDate = findViewById(R.id.scheduleTherapyDate)
        scheduleTherapyType = findViewById(R.id.scheduleTherapyType)
    }


    private fun initLayout() {
        scheduleTherapyUserName.setText(TherapyOrder.orders.name)
        scheduleTherapyUserPhone.setText(TherapyOrder.orders.phone)
        scheduleTherapyUserAddress.setText(TherapyOrder.orders.address)
        scheduleTherapyType.setText(TherapyOrder.orders.sessionType)
        scheduleTherapyDate.setText(TherapyOrder.orders.date)
    }

    fun onBack(view: View) {
        val intent = Intent(this, ScheduleTherapyDetailsActivity::class.java)
        startActivity(intent)
    }

    fun onConfirm(view: View) {
        val intent = Intent(this, ConfirmationActivity::class.java)
        intent.putExtra("order", Order("Therapy", TherapyOrder.orders.providerName, R.drawable.therapy_service_1))
        startActivity(intent)
    }
}