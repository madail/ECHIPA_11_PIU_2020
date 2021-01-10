package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class PaymentMethodActivity : AppCompatActivity() {
    private val currency = arrayOf<String>("RON", "GBP", "EUR", "USD")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)

        val spinner: Spinner = findViewById(R.id.spinner)

        val currencyAdapter = ArrayAdapter(
            this, R.layout.support_simple_spinner_dropdown_item, currency
        )
        currencyAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = currencyAdapter
    }

    fun confirm(view: View) {
        val intent = Intent(this, YourTeachingServiceConfirmation::class.java)
        startActivity(intent)
    }


}