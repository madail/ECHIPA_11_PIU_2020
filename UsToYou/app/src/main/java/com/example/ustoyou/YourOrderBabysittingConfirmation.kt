package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.BabysittingOrder
import com.example.ustoyou.model.Order

class YourOrderBabysittingConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_order_babysitting_confirmation)
        supportActionBar?.title = "Your Order"

        val creditCardText: TextView =
            findViewById(R.id.yourBabysittingOrderConfirmationCreditCardText)
        val creditCardEditText: EditText =
            findViewById(R.id.yourBabysittingOrderConfirmationCreditCardEditText)
        val nameEditText: EditText = findViewById(R.id.yourBabysittingOrderConfirmationNameEditText)
        val phoneEditText: EditText =
            findViewById(R.id.yourBabysittingOrderConfirmationPhoneEditText)
        val childsEditText: EditText =
            findViewById(R.id.yourBabysittingOrderConfirmationChildsAgeEditText)
        val addressEditText: EditText =
            findViewById(R.id.yourBabysittingOrderConfirmationAddressEditText)
        val dateEditText: EditText = findViewById(R.id.yourBabysittingOrderConfirmationDateEditText)

        val order = intent.getSerializableExtra("babySittingOrder") as BabysittingOrder

        nameEditText.setText(order.name)
        phoneEditText.setText(order.phone)
        childsEditText.setText(order.childsAge)
        addressEditText.setText(order.address)
        dateEditText.setText(order.date)

        val cash = intent.getBooleanExtra("cash", false)
        if (cash) {
            creditCardEditText.visibility = View.GONE
            creditCardText.visibility = View.GONE
        } else {
            creditCardEditText.visibility = View.VISIBLE
            creditCardText.visibility = View.VISIBLE
        }
    }

    fun back(view: View) {
        val intent1 = Intent(this, YourOrderBabysittingActivity::class.java)
        intent1.putExtra("payBack", true)
        intent1.putExtra("babySittingOrder", intent.getSerializableExtra("babySittingOrder"))
        startActivity(intent1)
    }

    fun order(view: View) {
        val intent1 = Intent(this, ConfirmationActivity::class.java)
        val babysittingOrder = intent.getSerializableExtra("babySittingOrder") as BabysittingOrder
        val order = Order("Babysitting",babysittingOrder.name, intent.getIntExtra("image",-1))
        intent1.putExtra("order",order)
        startActivity(intent1)
    }
}