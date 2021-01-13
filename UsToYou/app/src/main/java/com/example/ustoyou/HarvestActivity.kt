package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.CurrentPrice
import com.example.ustoyou.model.HarvestConfirmationDetails
import com.example.ustoyou.payment.PaymentMethodActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class HarvestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_harvest_info)
        supportActionBar?.title = "Corn Harvest Info"
    }

    fun goToPaymentMethod(view: View) {
        val name = intent.getStringExtra("name")
        val price: Int = intent.getIntExtra("price", -1)

        val availability = findViewById<EditText>(R.id.harvest_info_availabilityEditText)
        val surface = findViewById<EditText>(R.id.harvest_info_surfaceEditText)
        val address = findViewById<EditText>(R.id.harvest_info_addressEditText)
        val surface1= surface?.text.toString().split(" ")[0]
        var total=price*(surface1).toInt()
        val harvestDetails= HarvestConfirmationDetails(
            name.toString().substring(6),
            address.text.toString(),
            total,
            availability.text.toString(),
            surface.text.toString()
        )

        val isValid = validateDetails(
            surface,
            address,
            availability
        )

        if (isValid) {

            val intent = Intent(this, PaymentMethodActivity::class.java).apply {
                putExtra("HarvestInfo", harvestDetails)
                CurrentPrice.price=total
                putExtra("activity", "Harvest")
            }
            startActivity(intent)
        }
    }

    private fun validateDetails(
        surface: EditText,
        address: EditText,
        availability: EditText
    ): Boolean {
        if (surface.text.toString().isEmpty()) {
            surface.error = "Surface required!"
            return false
        }

        if (address.text.toString().isEmpty()) {
            address.error = "Address required!"
            return false
        }

        if (availability.text.toString().isEmpty()) {
            availability.error = "Availability required!"
            return false
        }

        return true
    }
}