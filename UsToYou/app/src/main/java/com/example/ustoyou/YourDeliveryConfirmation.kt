package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ustoyou.adapters.DeliveryAdapter
import com.example.ustoyou.model.*

class YourDeliveryConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_delivery_confirmation)

        val deliveryOrderName = intent.getStringExtra("deliveryOrderName")
        val deliveryOrderPhone = intent.getStringExtra("deliveryOrderPhone")
        val deliveryOrderAddress = intent.getStringExtra("deliveryOrderAddress")

        val nameEditText: EditText = findViewById(R.id.pizzaDeliveryConfirmationNameEditText)
        val phoneEditText:EditText = findViewById(R.id.pizzaDeliveryConfirmationPhoneEditText)
        val addressEditText:EditText = findViewById(R.id.pizzaDeliveryConfirmationAddressEditText)

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

        val creditCardEditText: TextView = findViewById(R.id.yourDeliveryConfirmationCreditCardEditText)
        val creditCardText: TextView = findViewById(R.id.yourDeliveryConfirmationCreditCardText)

        val cash = intent.getBooleanExtra("cash", false)
        if (cash) {
            creditCardEditText.visibility = View.GONE
            creditCardText.visibility = View.GONE
        } else {
            creditCardEditText.visibility = View.VISIBLE
            creditCardText.visibility = View.VISIBLE
        }

        val price: TextView = findViewById(R.id.pizzaDeliveryConfirmationTotal)
        price.text = "Price: ${intent.getStringExtra("total")}$"
    }

    fun back(view: View) {
        //TODO
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
}