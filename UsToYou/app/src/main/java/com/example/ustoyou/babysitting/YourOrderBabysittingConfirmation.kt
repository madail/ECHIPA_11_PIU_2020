package com.example.ustoyou.babysitting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.ConfirmationActivity
import com.example.ustoyou.DeclinedActivity
import com.example.ustoyou.R
import com.example.ustoyou.model.BabysittingOrder
import com.example.ustoyou.model.Order
import com.example.ustoyou.model.User
import com.example.ustoyou.payment.PaymentDetailsActivity

class YourOrderBabysittingConfirmation : AppCompatActivity() {
    private var cardString: String = "XXXX-XXXX-XXXX-XXXX MM/YY CVV"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_order_babysitting_confirmation)
        supportActionBar?.title = "Your Order"

        val creditCardText: TextView =
            findViewById(R.id.yourDeliveryConfirmationCreditCardText)
        val creditCardEditText: EditText =
            findViewById(R.id.yourDeliveryConfirmationCreditCardEditText)
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

        creditCardEditText.setOnClickListener {
            val intent1 = Intent(this, PaymentDetailsActivity::class.java)
            startActivityForResult(intent1, 1234)
        }

        if(intent.getStringExtra("card") != null) {
            cardString = intent.getStringExtra("card")!!
        }

        creditCardEditText.setText(cardString)
    }

    fun back(view: View) {
        val intent1 = Intent(this, YourOrderBabysittingActivity::class.java)
        intent1.putExtra("payBack", true)
        intent1.putExtra("name", intent.getStringExtra("name"))
        intent1.putExtra("image", intent.getIntExtra("image", -1))
        intent1.putExtra("babySittingOrder", intent.getSerializableExtra("babySittingOrder"))
        intent1.putExtra("card", cardString)
        startActivity(intent1)
    }

    fun order(view: View) {
        if(User.currentUser?.cash!!) {
            if(cardString != "XXXX-XXXX-XXXX-XXXX MM/YY CVV") {
                val intent1 = Intent(this, ConfirmationActivity::class.java)
                val order =
                    Order(
                        "Babysitting",
                        intent.getStringExtra("name")!!,
                        intent.getIntExtra("image", -1)
                    )
                intent1.putExtra("order", order)
                startActivity(intent1)
                finish()
            }else{
                Toast.makeText(this,"CARD REQUIRED",Toast.LENGTH_LONG).show()
            }
        }else{
            if(cardString != "XXXX-XXXX-XXXX-XXXX MM/YY CVV") {
                val intent = Intent(this, DeclinedActivity::class.java)
                intent.putExtra("activity", "babysitting")
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"CARD REQUIRED",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1234) {
            if (resultCode == Activity.RESULT_OK) {
                val cardNumber = data!!.getStringExtra("cardNumber")
                val expDate = data.getStringExtra("expirationDate")
                val cvc = data.getStringExtra("cvc")

                val creditCardEditText: EditText =
                    findViewById(R.id.yourDeliveryConfirmationCreditCardEditText)

                cardString = "$cardNumber $expDate $cvc"

                creditCardEditText.setText(cardString)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}