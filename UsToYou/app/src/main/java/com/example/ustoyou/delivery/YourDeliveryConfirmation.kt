package com.example.ustoyou.delivery

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ustoyou.ConfirmationActivity
import com.example.ustoyou.payment.PaymentDetailsActivity
import com.example.ustoyou.R
import com.example.ustoyou.adapters.DeliveryAdapter
import com.example.ustoyou.babysitting.YourOrderBabysittingActivity
import com.example.ustoyou.model.DeliveryObjects
import com.example.ustoyou.model.Order

class YourDeliveryConfirmation : AppCompatActivity() {
    private var cardString: String = "XXXX-XXXX-XXXX-XXXX MM/YY CVV"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_delivery_confirmation)

        val deliveryOrderName = intent.getStringExtra("deliveryOrderName")
        val deliveryOrderPhone = intent.getStringExtra("deliveryOrderPhone")
        val deliveryOrderAddress = intent.getStringExtra("deliveryOrderAddress")

        val nameEditText: EditText = findViewById(R.id.pizzaDeliveryConfirmationNameEditText)
        val phoneEditText: EditText = findViewById(R.id.pizzaDeliveryConfirmationPhoneEditText)
        val addressEditText: EditText = findViewById(R.id.pizzaDeliveryConfirmationAddressEditText)

        nameEditText.setText(deliveryOrderName)
        phoneEditText.setText(deliveryOrderPhone)
        addressEditText.setText(deliveryOrderAddress)

        val recyclerView: RecyclerView = findViewById(R.id.rv_pizzaDetailsDelivery)
        val pizzaDeliveryAdapter = DeliveryAdapter(
            DeliveryObjects.objects,
            this
        )
        recyclerView.adapter = pizzaDeliveryAdapter

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager

        val creditCardEditText: TextView =
            findViewById(R.id.yourDeliveryConfirmationCreditCardEditText)
        val creditCardText: TextView = findViewById(R.id.yourDeliveryConfirmationCreditCardText)

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

        val price: TextView = findViewById(R.id.pizzaDeliveryConfirmationTotal)
        price.text = "Price: ${intent.getStringExtra("total")}$"

        if(intent.getStringExtra("card") != null) {
            cardString = intent.getStringExtra("card")!!
        }

        creditCardEditText.setText(cardString)
    }

    fun back(view: View) {
        val intent1 = Intent(this, YourDeliveryActivity::class.java)
        intent1.putExtra("payBack", true)
        intent1.putExtra("name", intent.getStringExtra("name"))
        intent1.putExtra("image", intent.getIntExtra("image", -1))
        intent1.putExtra("deliveryOrder", intent.getSerializableExtra("deliveryOrder"))
        intent1.putExtra("card", cardString)
        startActivity(intent1)
    }

    fun order(view: View) {
        val intent1 = Intent(this, ConfirmationActivity::class.java)
        var name = intent.getStringExtra("name")
        if (name == null) {
            name = ""
        }
        val order = Order("Delivery", name, intent.getIntExtra("image", -1))
        intent1.putExtra("order", order)
        startActivity(intent1)
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