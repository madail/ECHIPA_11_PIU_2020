package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.TeachingServiceOrderDetails
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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

        val isValid = validateDetails(
            nameEditText,
            phoneEditText,
            addressEditText,
            dateEditText
        )

        if (isValid) {

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

    private fun validateDetails(
        name: EditText,
        phone: EditText,
        address: EditText,
        date: EditText
    ): Boolean {
        if (name.text.toString().isEmpty()) {
            name.error = "Name required!"
            return false
        }

        if (phone.text.toString().isEmpty()) {
            phone.error = "Phone required!"
            return false
        } else {
            if (phone.text.toString().length != 10) {
                phone.error = "Phone should contains 10 figure!"
                return false
            }
        }

        if (address.text.toString().isEmpty()) {
            address.error = "Address required!"
            return false
        }

        if (date.text.toString().isEmpty()) {
            date.error = "Date required!"
            return false
        } else {
            try {
                val formatter = SimpleDateFormat("dd/MM/yyyy HH:MM", Locale.getDefault())
                val myDate = formatter.parse(date.text.toString())
                val current = Date()
                if (!current.before(myDate)) {
                    date.error = "The date should be in the future!"
                    return false
                }
            } catch (ex: ParseException) {
                date.error = "Format should be DD/MM/YYYY HH:MM"
                return false
            }
        }


        return true
    }
}