package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class PaymentMethodActivity : AppCompatActivity() {
    val currency = arrayOf<String>("RON", "GBP", "EUR", "USD")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)

        val spinner : Spinner =findViewById(R.id.payment_method_spinner)

        if (spinner != null) {
            val currencyAdapter = ArrayAdapter(
                this, R.layout.support_simple_spinner_dropdown_item, currency
            )
            currencyAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinner.adapter = currencyAdapter
        }
    }


    fun goToPayment(view: View) {
        val  cashPayment : RadioButton = findViewById(R.id.payment_method_cash)
        val  cardPayment : RadioButton = findViewById(R.id.payment_method_card)
        if(cashPayment.isChecked)
        {
            val intent = Intent(this, ConfirmationActivity::class.java)
            startActivity(intent)
        }

    }
}