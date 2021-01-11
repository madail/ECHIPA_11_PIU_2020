package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.BabysittingOrder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class YourOrderBabysittingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_order_babysitting)
        supportActionBar?.title = "Your Order"

        val nameEditText: EditText = findViewById(R.id.yourBabysittingOrderNameEditText)
        val phoneEditText: EditText = findViewById(R.id.yourBabysittingOrderPhoneEditText)
        val addressEditText: EditText = findViewById(R.id.yourBabysittingOrderAddressEditText)
        val dateEditText: EditText = findViewById(R.id.yourBabysittingOrderDateEditText)
        val childEditText: EditText = findViewById(R.id.yourBabysittingOrderChildAgeEditText)

        if (intent.getBooleanExtra("payBack", false)) {
            val order = intent.getSerializableExtra("babySittingOrder") as BabysittingOrder

            nameEditText.setText(order.name)
            phoneEditText.setText(order.phone)
            addressEditText.setText(order.address)
            dateEditText.setText(order.date)
            childEditText.setText(order.childsAge)
        }
    }

    fun payOrder(view: View) {
        val nameEditText: EditText = findViewById(R.id.yourBabysittingOrderNameEditText)
        val phoneEditText: EditText = findViewById(R.id.yourBabysittingOrderPhoneEditText)
        val addressEditText: EditText = findViewById(R.id.yourBabysittingOrderAddressEditText)
        val dateEditText: EditText = findViewById(R.id.yourBabysittingOrderDateEditText)
        val childEditText: EditText = findViewById(R.id.yourBabysittingOrderChildAgeEditText)

        val isValid = validateDetails(
            nameEditText,
            phoneEditText,
            addressEditText,
            dateEditText,
            childEditText
        )

        if (isValid) {

            val intent = Intent(this, PaymentMethodActivity::class.java)

            val babysittingOrder = BabysittingOrder(
                nameEditText.text.toString(),
                phoneEditText.text.toString(),
                addressEditText.text.toString(),
                childEditText.text.toString(),
                dateEditText.text.toString()
            )

            intent.putExtra("babySittingOrder", babysittingOrder)

            startActivity(intent)
        }
    }

    private fun validateDetails(
        name: EditText,
        phone: EditText,
        address: EditText,
        date: EditText,
        childsAge: EditText
    ): Boolean {
        if (childsAge.text.toString().isEmpty()) {
            childsAge.error = "Child's age required!"
            return false
        }

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