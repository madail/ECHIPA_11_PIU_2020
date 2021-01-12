package com.example.ustoyou

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.HarvestConfirmationDetails
import com.example.ustoyou.model.ITConfirmationDetails

class ITConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_it)

        val details = intent.getSerializableExtra("ITInfo") as ITConfirmationDetails

        val name: EditText = findViewById<EditText>(R.id.itConfirmationNameEditText)
        val address: EditText = findViewById<EditText>(R.id.itConfirmationAddressEditText)
        val price: EditText = findViewById<EditText>(R.id.itConfirmationPriceEditText)
        val availability: EditText = findViewById<EditText>(R.id.itConfirmationAvailabilityEditText)
        val id: EditText = findViewById<EditText>(R.id.itConfirmationIdEditText)
        val password: EditText = findViewById<EditText>(R.id.itConfirmationPasswordEditText)


        name.setText(details.name)
        address.setText(details.address)
        price.setText(details.price)
        availability.setText(details.availability)
        id.setText(details.teamViewerID)
        password.setText(details.teamViewerIPassword)
    }
}