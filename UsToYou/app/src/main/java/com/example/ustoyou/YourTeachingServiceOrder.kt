package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.TeachingServiceOrderDetails

class YourTeachingServiceOrder : AppCompatActivity() {
    var types = arrayOf<String>("Online", "Onsite")
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_teaching_service_order)
        supportActionBar?.title = "Your Order"

        spinner = findViewById(R.id.yourTeachingOrderTypeSpinner)

        val currencyAdapter = ArrayAdapter(
            this, R.layout.support_simple_spinner_dropdown_item, types
        )
        currencyAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = currencyAdapter

        val nameEditText: EditText = findViewById(R.id.yourTeachingOrderNameEditText)
        val phoneEditText: EditText = findViewById(R.id.yourTeachingOrderPhoneEditText)
        val addressEditText: EditText = findViewById(R.id.yourTeachingOrderAddressEditText)
        val dateEditText: EditText = findViewById(R.id.yourTeachingOrderDateEditText)

        if (intent.getBooleanExtra("payBack", false)) {
            val order = intent.getSerializableExtra("teachingOrder") as TeachingServiceOrderDetails

            nameEditText.setText(order.name)
            phoneEditText.setText(order.phone)
            addressEditText.setText(order.address)
            dateEditText.setText(order.date)
            if (order.type == "Online") {
                spinner.setSelection(0)
            } else {
                spinner.setSelection(1)
            }
        }
    }

    fun payOrder(view: View) {
        val nameEditText: EditText = findViewById(R.id.yourTeachingOrderNameEditText)
        val phoneEditText: EditText = findViewById(R.id.yourTeachingOrderPhoneEditText)
        val addressEditText: EditText = findViewById(R.id.yourTeachingOrderAddressEditText)
        val dateEditText: EditText = findViewById(R.id.yourTeachingOrderDateEditText)

        val intent = Intent(this, PaymentMethodActivity::class.java)

        val teachingServiceOrder = TeachingServiceOrderDetails(
            nameEditText.text.toString(),
            phoneEditText.text.toString(),
            spinner.selectedItem.toString(),
            addressEditText.text.toString(),
            dateEditText.text.toString()
        )

        intent.putExtra("teachingOrder", teachingServiceOrder)

        startActivity(intent)
    }
}