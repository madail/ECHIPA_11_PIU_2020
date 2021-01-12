package com.example.ustoyou

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.WoodCuttingConfirmationDetails

class WoodCuttingConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_wood_cutting)

        val details = intent.getSerializableExtra("WoodInfo") as WoodCuttingConfirmationDetails

        val name: EditText = findViewById<EditText>(R.id.woodConfirmationNameEditText)
        val address: EditText = findViewById<EditText>(R.id.woodConfirmationAddressEditText)
        val price: EditText = findViewById<EditText>(R.id.woodConfirmationPriceEditText)
        val availability: EditText = findViewById<EditText>(R.id.woodConfirmationAvailabilityEditText)
        val quantity: EditText = findViewById<EditText>(R.id.woodConfirmationQuantityEditText)


        name.setText(details.name)
        address.setText(details.address)
        price.setText(details.price)
        availability.setText(details.availability)
        quantity.setText(details.quantity)
    }
}