package com.example.ustoyou

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.HarvestConfirmationDetails
import com.example.ustoyou.model.WoodCuttingConfirmationDetails

class HarvestConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_harvest)

        val details = intent.getSerializableExtra("HarvestInfo") as HarvestConfirmationDetails

        val name: EditText = findViewById<EditText>(R.id.harvestConfirmationNameEditText)
        val address: EditText = findViewById<EditText>(R.id.harvestConfirmationAddressEditText)
        val price: EditText = findViewById<EditText>(R.id.harvestConfirmationPriceEditText)
        val availability: EditText = findViewById<EditText>(R.id.harvestConfirmationAvailabilityEditText)
        val surface: EditText = findViewById<EditText>(R.id.harvestConfirmationSurfaceEditText)


        name.setText(details.name)
        address.setText(details.address)
        price.setText(details.price)
        availability.setText(details.availability)
        surface.setText(details.surface)
    }
}