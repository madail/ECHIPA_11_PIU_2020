package com.example.ustoyou

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.HarvestConfirmationDetails
import com.example.ustoyou.model.ITConfirmationDetails
import com.example.ustoyou.model.Order
import com.example.ustoyou.payment.PaymentDetailsActivity

class ITConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_it)

        val details = intent.getSerializableExtra("ITInfo") as ITConfirmationDetails
        System.out.println("fhfhfhdhhdhd" + details.price)
        val cashPayment = intent.getBooleanExtra("cash", false)

        val card: TextView = findViewById<EditText>(R.id.itConfirmationCreditCardText)
        val cardDetails: EditText = findViewById<EditText>(R.id.itConfirmationCreditCardEditText)

        if(cashPayment){
            card.visibility= View.GONE
            cardDetails.visibility= View.GONE
        } else {
            card.visibility = View.VISIBLE
            cardDetails.visibility = View.VISIBLE
        }

        val name: EditText = findViewById<EditText>(R.id.itConfirmationNameEditText)
        val address: EditText = findViewById<EditText>(R.id.itConfirmationAddressEditText)
        val price: EditText = findViewById<EditText>(R.id.itConfirmationPriceEditText)
        val availability: EditText = findViewById<EditText>(R.id.itConfirmationAvailabilityEditText)
        val id: EditText = findViewById<EditText>(R.id.itConfirmationIdEditText)
        val password: EditText = findViewById<EditText>(R.id.itConfirmationPasswordEditText)


        name.setText(details.name)
        address.setText(details.address)
        price.setText(details.price.toString())
        availability.setText(details.availability)
        id.setText(details.teamViewerID)
        password.setText(details.teamViewerIPassword)

        cardDetails.setOnClickListener {
            val intent = Intent(this, PaymentDetailsActivity::class.java)
            startActivityForResult(intent, 1234)
        }
    }

    fun confirm(view: View) {
        val cashPayment = intent.getBooleanExtra("cash", false)
        val cardDetails: EditText = findViewById<EditText>(R.id.itConfirmationCreditCardEditText)
        val details = intent.getSerializableExtra("ITInfo") as ITConfirmationDetails
        val order = Order("IT Support",details.name,R.drawable.it_support_service)

        if(cashPayment)
        {
            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra("order",order)
            startActivity(intent)
        }else if(cardDetails.text.toString()!="")
        {
            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra("order",order)
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

                val cardDetails: EditText = findViewById<EditText>(R.id.itConfirmationCreditCardEditText)

                val cardString = "$cardNumber $expDate $cvc"

                cardDetails.setText(cardString)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}