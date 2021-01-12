package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.ITConfirmationDetails

class ITSupportActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_it_support_info)


    }

    fun goToPaymentMethod(view: View) {
        val name = intent.getStringExtra("name")
        val address = intent.getStringExtra("address")
        val price: Int = intent.getIntExtra("price", -1)

        val availability = findViewById<EditText>(R.id.it_support_availabilityEditText)
        val teamViewerId = findViewById<EditText>(R.id.it_support_idEditText)
        val teamViewerPassword = findViewById<EditText>(R.id.it_support_passwordEditText)

        val itConfirmationDetails= ITConfirmationDetails(
            name.toString(),
            address.toString(),
            price,
            availability.text.toString(),
            teamViewerId.text.toString(),
            teamViewerPassword.text.toString()
        )

        val intent1 = Intent(this, PaymentMethodActivity::class.java).apply {
            putExtra("ITInfo",itConfirmationDetails)
            putExtra("activity", "IT")
        }
        startActivity(intent1)
    }
}