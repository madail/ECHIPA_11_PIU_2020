package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class DeclinedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_declined)
    }

    fun payment(view: View) {
        val intent = Intent(this, PaymentMethodActivity::class.java)
        startActivity(intent)
    }
}