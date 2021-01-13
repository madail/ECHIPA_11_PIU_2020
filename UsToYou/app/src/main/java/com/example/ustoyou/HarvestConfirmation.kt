package com.example.ustoyou

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.HarvestConfirmationDetails
import com.example.ustoyou.model.WoodCuttingConfirmationDetails
import com.example.ustoyou.payment.PaymentDetailsActivity
import com.example.ustoyou.payment.PaymentMethodActivity

class HarvestConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_harvest)

        supportActionBar?.title = "Corn Harvest Confirmation"

        val details = intent.getSerializableExtra("HarvestInfo") as HarvestConfirmationDetails
        val cashPayment = intent.getBooleanExtra("cash", false)

        val card: TextView = findViewById<EditText>(R.id.harvestConfirmationCreditCardText)
        val cardDetails: EditText = findViewById<EditText>(R.id.harvestConfirmationCreditCardEditText)

        if(cashPayment){
            card.visibility= View.GONE
            cardDetails.visibility=View.GONE
        } else {
            card.visibility = View.VISIBLE
            cardDetails.visibility = View.VISIBLE
        }

        val name: EditText = findViewById<EditText>(R.id.harvestConfirmationNameEditText)
        val address: EditText = findViewById<EditText>(R.id.harvestConfirmationAddressEditText)
        val price: EditText = findViewById<EditText>(R.id.harvestConfirmationPriceEditText)
        val availability: EditText = findViewById<EditText>(R.id.harvestConfirmationAvailabilityEditText)
        val surface: EditText = findViewById<EditText>(R.id.harvestConfirmationSurfaceEditText)


        name.setText(details.name)
        address.setText(details.address)
        price.setText(details.price.toString())
        availability.setText(details.availability)
        surface.setText(details.surface)

        cardDetails.setOnClickListener {
            val intent = Intent(this, PaymentDetailsActivity::class.java)
            startActivityForResult(intent, 1234)
        }
    }

    fun confirm(view: View) {
        val cashPayment = intent.getBooleanExtra("cash", false)
        val cardDetails: EditText = findViewById<EditText>(R.id.harvestConfirmationCreditCardEditText)

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

                val cardDetails: EditText = findViewById<EditText>(R.id.harvestConfirmationCreditCardEditText)

                val cardString = "$cardNumber $expDate $cvc"

                cardDetails.setText(cardString)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}