package com.example.ustoyou

import android.content.Intent
import android.os.Bundle

import android.widget.RadioButton
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter

import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class PaymentMethodActivity : AppCompatActivity() {

    lateinit var spinner: Spinner
    private val currency = arrayOf<String>("RON", "GBP", "EUR", "USD")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)

        spinner = findViewById(R.id.payment_method_spinner)

        val currencyAdapter = ArrayAdapter(
            this, R.layout.support_simple_spinner_dropdown_item, currency
        )
        currencyAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = currencyAdapter
    }

    fun confirm(view: View) {
        val teachingServiceOrderDetails = intent.getSerializableExtra("teachingOrder")
        val radioGroup: RadioGroup = findViewById(R.id.payMethodRadioButtons)
        val radioButtonSelected = resources.getResourceEntryName(radioGroup.checkedRadioButtonId)
        Log.d("PAYMENT", radioButtonSelected)

        val intent = Intent(this, YourTeachingServiceConfirmation::class.java)

        intent.putExtra("teachingOrder", teachingServiceOrderDetails)
        if (radioButtonSelected == "cash") {
            intent.putExtra("cash", true)
        }

        startActivity(intent)
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