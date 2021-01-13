package com.example.ustoyou

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.WoodCuttingConfirmationDetails
import com.example.ustoyou.payment.PaymentDetailsActivity

class WoodCuttingConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_wood_cutting)

        val details = intent.getSerializableExtra("WoodInfo") as WoodCuttingConfirmationDetails
        val cashPayment = intent.getBooleanExtra("cash", false)

        val card: TextView = findViewById<EditText>(R.id.woodConfirmationCreditCardText)
        val cardDetails: EditText = findViewById<EditText>(R.id.woodConfirmationCreditCardEditText)

        if(cashPayment){
            card.visibility= View.GONE
            cardDetails.visibility= View.GONE
        } else {
            card.visibility = View.VISIBLE
            cardDetails.visibility = View.VISIBLE
        }

        val name: EditText = findViewById<EditText>(R.id.woodConfirmationNameEditText)
        val address: EditText = findViewById(R.id.woodConfirmationAddressEditText)
        val price: EditText = findViewById<EditText>(R.id.woodConfirmationPriceEditText)
        val availability: EditText = findViewById<EditText>(R.id.woodConfirmationAvailabilityEditText)
        val quantity: EditText = findViewById<EditText>(R.id.woodConfirmationQuantityEditText)

        name.setText(details.name)
        address.setText(details.address)
        price.setText(details.price.toString())
        availability.setText(details.availability)
        quantity.setText(details.quantity)

        cardDetails.setOnClickListener {
            val intent = Intent(this, PaymentDetailsActivity::class.java)
            startActivityForResult(intent, 1234)
        }
    }

    fun confirm(view: View) {
        val cashPayment = intent.getBooleanExtra("cash", false)
        val cardDetails: EditText = findViewById<EditText>(R.id.woodConfirmationCreditCardEditText)

        if(cashPayment)
        {
            val intent = Intent(this, ConfirmationActivity::class.java)
            startActivity(intent)
        }else if(cardDetails.text.toString()!="")
        {
            val intent = Intent(this, ConfirmationActivity::class.java)
            startActivity(intent)
        }else{
            cardDetails.error = "Card Number is required!"
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1234) {
            if (resultCode == Activity.RESULT_OK) {
                val cardNumber = data!!.getStringExtra("cardNumber")
                val expDate = data.getStringExtra("expirationDate")
                val cvc = data.getStringExtra("cvc")

                val cardDetails: EditText = findViewById<EditText>(R.id.woodConfirmationCreditCardEditText)

                val cardString = "$cardNumber $expDate $cvc"

                cardDetails.setText(cardString)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}