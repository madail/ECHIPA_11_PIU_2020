package com.example.ustoyou.teaching

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
import com.example.ustoyou.model.Order
import com.example.ustoyou.model.TeachingServiceOrderDetails
import com.example.ustoyou.model.User
import com.example.ustoyou.payment.PaymentDetailsActivity

class YourTeachingServiceConfirmation : AppCompatActivity() {
    private lateinit var order: Order
    private var cardString: String = "XXXX-XXXX-XXXX-XXXX MM/YY CVV"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_teaching_service_confirmation)
        supportActionBar?.title = "Your Order"

        val creditCardText: TextView =
            findViewById(R.id.yourTeachingOrderConfirmationCreditCardText)
        val creditCardEditText: EditText =
            findViewById(R.id.yourTeachingOrderConfirmationCreditCardEditText)
        val nameEditText: EditText = findViewById(R.id.yourTeachingOrderConfirmationNameEditText)
        val phoneEditText: EditText = findViewById(R.id.yourTeachingOrderConfirmationPhoneEditText)
        val typeEditText: EditText = findViewById(R.id.yourTeachingOrderConfirmationTypeEditText)
        val addressEditText: EditText =
            findViewById(R.id.yourTeachingOrderConfirmationAddressEditText)
        val dateEditText: EditText = findViewById(R.id.yourTeachingOrderConfirmationDateEditText)

        val extraOrder = intent.getSerializableExtra("teachingOrder") as TeachingServiceOrderDetails

        nameEditText.setText(extraOrder.name)
        phoneEditText.setText(extraOrder.phone)
        typeEditText.setText(extraOrder.type)
        addressEditText.setText(extraOrder.address)
        dateEditText.setText(extraOrder.date)

        order = Order(
            "Teaching", "John Doe", intent.getIntExtra("image", -1),
            extraOrder.date, extraOrder.type, "Credit Card"
        )

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


        if (intent.getStringExtra("card") != null) {
            cardString = intent.getStringExtra("card")!!
        }

        creditCardEditText.setText(cardString)
    }

    fun order(view: View) {
        if (User.currentUser?.cash!!) {
            if (cardString != "XXXX-XXXX-XXXX-XXXX MM/YY CVV" || intent.getBooleanExtra("cash", false)) {
                val intent1 = Intent(this, ConfirmationActivity::class.java)
                val newOrder = Order(
                    "Teaching", intent.getStringExtra("name")!!,
                    intent.getIntExtra("image", -1), order.date, order.type, order.paymentType
                )
                intent1.putExtra("order", newOrder)
                startActivity(intent1)
                finish()
            } else {
                Toast.makeText(this, "CARD REQUIRED", Toast.LENGTH_LONG).show()
            }
        } else {
            if (cardString != "XXXX-XXXX-XXXX-XXXX MM/YY CVV") {
                val intent = Intent(this, DeclinedActivity::class.java)
                intent.putExtra("activity", "teaching")
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "CARD REQUIRED", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun back(view: View) {
        val intent1 = Intent(this, YourTeachingServiceOrder::class.java)
        intent1.putExtra("payBack", true)
        intent1.putExtra("name", intent.getStringExtra("name"))
        intent1.putExtra("image", intent.getIntExtra("image", -1))
        intent1.putExtra("card", cardString)
        intent1.putExtra("teachingOrder", intent.getSerializableExtra("teachingOrder"))
        startActivity(intent1)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1234) {
            if (resultCode == Activity.RESULT_OK) {
                val cardNumber = data!!.getStringExtra("cardNumber")
                val expDate = data.getStringExtra("expirationDate")
                val cvc = data.getStringExtra("cvc")

                val creditCardEditText: EditText =
                    findViewById(R.id.yourTeachingOrderConfirmationCreditCardEditText)

                cardString = "$cardNumber $expDate $cvc"

                creditCardEditText.setText(cardString)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}