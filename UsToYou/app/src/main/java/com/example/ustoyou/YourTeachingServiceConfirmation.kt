package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.Order
import com.example.ustoyou.model.TeachingServiceOrderDetails

class YourTeachingServiceConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_teaching_service_confirmation)
        supportActionBar?.title = "Your Order"

        val creditCardText: TextView =
            findViewById(R.id.yourTeachingOrderConfirmationCreditCardText)
        val creditCardEditText: EditText =
            findViewById(R.id.yourTeachingOrderConfirmationCreditCardEditText)
        val nameEditText : EditText = findViewById(R.id.yourTeachingOrderConfirmationNameEditText)
        val phoneEditText : EditText = findViewById(R.id.yourTeachingOrderConfirmationPhoneEditText)
        val typeEditText : EditText = findViewById(R.id.yourTeachingOrderConfirmationTypeEditText)
        val addressEditText : EditText = findViewById(R.id.yourTeachingOrderConfirmationAddressEditText)
        val dateEditText : EditText = findViewById(R.id.yourTeachingOrderConfirmationDateEditText)

        val order = intent.getSerializableExtra("teachingOrder") as TeachingServiceOrderDetails

        nameEditText.setText(order.name)
        phoneEditText.setText(order.phone)
        typeEditText.setText(order.type)
        addressEditText.setText(order.address)
        dateEditText.setText(order.date)

        val cash = intent.getBooleanExtra("cash", false)
        if (cash) {
            creditCardEditText.visibility = View.GONE
            creditCardText.visibility = View.GONE
        }else{
            creditCardEditText.visibility = View.VISIBLE
            creditCardText.visibility = View.VISIBLE
        }

        creditCardEditText.setOnClickListener {
            val intent1 = Intent(this, PaymentDetailsActivity::class.java)
            startActivity(intent1)
        }

    }

    fun order(view: View) {
        val intent1 = Intent(this, ConfirmationActivity::class.java)
        val order = Order("Babysitting",intent.getStringExtra("name")!!, intent.getIntExtra("image",-1))
        intent1.putExtra("order",order)
        startActivity(intent1)
    }

    fun back(view: View) {
        val intent1 = Intent(this, YourTeachingServiceOrder::class.java)
        intent1.putExtra("payBack",true)
        intent1.putExtra("name",intent.getStringExtra("name"))
        intent1.putExtra("image",intent.getIntExtra("image",-1))
        intent1.putExtra("teachingOrder", intent.getSerializableExtra("teachingOrder"))
        startActivity(intent1)
    }
}