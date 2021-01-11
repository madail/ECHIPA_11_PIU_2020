package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ustoyou.adapters.DeliveryAdapter
import com.example.ustoyou.model.DeliveryObject
import com.example.ustoyou.model.Pizzas
import com.example.ustoyou.model.Shoes
import com.example.ustoyou.model.Sushi

class YourDeliveryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_delivery)
        supportActionBar?.title = "Your Order"

        var deliveryServices: ArrayList<DeliveryObject> = ArrayList()
        val type = intent.getIntExtra("typeOfDelivery", -1)

        if (type == 0) {
            deliveryServices = Pizzas().getPizzas()
        } else if (type == 1) {
            deliveryServices = Sushi().getSushi()
        } else {
            deliveryServices = Shoes().getShoes()
        }

        val recyclerView: RecyclerView = findViewById(R.id.rv_pizzaDetailsDelivery)
        val pizzaDeliveryAdapter = DeliveryAdapter(
            deliveryServices,
            this
        )
        recyclerView.adapter = pizzaDeliveryAdapter

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager
    }

    fun payOrder(view: View) {
        val name: EditText = findViewById(R.id.pizzaDeliveryNameEditText)
        val phone: EditText = findViewById(R.id.pizzaDeliveryPhoneEditText)
        val address: EditText = findViewById(R.id.pizzaDeliveryAddressEditText)

        val isValid = validateDetails(name, phone, address)

        if (isValid) {

            val intent1 = Intent(this, PaymentMethodActivity::class.java)
            intent1.putExtra("pizza", "pizza")
            intent1.putExtra("image", intent.getIntExtra("image", -1))
            intent1.putExtra("name", intent.getStringExtra("name"))
            startActivity(intent1)
        }

    }

    private fun validateDetails(
        name: EditText,
        phone: EditText,
        address: EditText
    ): Boolean {
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

        return true
    }
}