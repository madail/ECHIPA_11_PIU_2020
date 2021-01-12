package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.HarvestConfirmationDetails
import com.example.ustoyou.model.ITConfirmationDetails

class HarvestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_harvest_info)
    }

    fun goToPaymentMethod(view: View) {
        val name = intent.getStringExtra("name")
        val address = intent.getStringExtra("address")
        val price: Int = intent.getIntExtra("price", -1)

        val availability = findViewById<EditText>(R.id.harvest_info_availabilityEditText)
        val surface = findViewById<EditText>(R.id.harvest_info_surfaceEditText)
        val Surface= surface?.text.toString().split(" ")[0]
        var total=price*(Surface).toInt()
        val harvestDetails= HarvestConfirmationDetails(
            name.toString(),
            address.toString(),
            total,
            availability.text.toString(),
            surface.text.toString()
        )

        val intent1 = Intent(this, PaymentMethodActivity::class.java).apply {
            putExtra("HarvestInfo",harvestDetails)
            putExtra("activity", "Harvest")
        }
        startActivity(intent1)
    }
}