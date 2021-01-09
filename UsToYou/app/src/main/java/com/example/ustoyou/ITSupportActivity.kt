package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ITSupportActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_it_support_info)
    }

    fun goToPaymentMethod(view: View) {
        val intent = Intent(this, PaymentMethodActivity::class.java)
        startActivity(intent)
    }
}