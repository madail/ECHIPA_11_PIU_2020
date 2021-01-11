package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TherapyServiceForm2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_therapy_service_form2)
        supportActionBar?.title = "New therapy service"
    }

    fun continueToNextActivity(view:View) {
        val intent = Intent(this, ConfirmServiceActivity::class.java)
        startActivity(intent)
    }
}