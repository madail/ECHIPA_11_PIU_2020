package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.HarvestConfirmationDetails
import com.example.ustoyou.model.WoodCuttingConfirmationDetails

class WoodCutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wood_info)
    }

    fun goToPaymentMethod(view: View) {
        val name = intent.getStringExtra("name")
        val address = intent.getStringExtra("address")
        val price: Int = intent.getIntExtra("price", -1)

        val availability = findViewById<EditText>(R.id.wood_info_availabilityEditText)
        val quantity = findViewById<EditText>(R.id.wood_info_quantityEditText)
        val Quantity= quantity?.text.toString().split(" ")[0]
        var total=price*(Quantity).toInt()
        val woodCuttingDetails= WoodCuttingConfirmationDetails(
            name.toString(),
            address.toString(),
            total,
            availability.text.toString(),
            quantity.text.toString()
        )

        val intent1 = Intent(this, PaymentMethodActivity::class.java).apply {
            putExtra("WoodInfo",woodCuttingDetails)
            putExtra("activity", "WoodCutting")
        }
        startActivity(intent1)
    }
}