package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.WoodCuttingConfirmationDetails
import com.example.ustoyou.payment.PaymentMethodActivity

class WoodCutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wood_info)
    }

    fun goToPaymentMethod(view: View) {
        val name = intent.getStringExtra("name")
        val price: Int = intent.getIntExtra("price", -1)

        val availability = findViewById<EditText>(R.id.wood_info_availabilityEditText)
        val quantity = findViewById<EditText>(R.id.wood_info_quantityEditText)
        val address = findViewById<EditText>(R.id.wood_info_addressEditText)
        val quantity1= quantity?.text.toString().split(" ")[0]
        var total=price*(quantity1).toInt()
        val woodCuttingDetails= WoodCuttingConfirmationDetails(
            name.toString().substring(6),
            address.text.toString(),
            total,
            availability.text.toString(),
            quantity.text.toString()
        )


        val isValid = validateDetails(
            quantity,
            address,
            availability
        )

        if (isValid) {

            val intent1 = Intent(this, PaymentMethodActivity::class.java).apply {
                putExtra("WoodInfo", woodCuttingDetails)
                putExtra("activity", "WoodCutting")
            }
            startActivity(intent1)
        }
    }

    private fun validateDetails(
        quantity: EditText,
        address: EditText,
        availability: EditText
    ): Boolean {
        if (quantity.text.toString().isEmpty()) {
            quantity.error = "Quantity required!"
            return false
        }

        if (availability.text.toString().isEmpty()) {
            availability.error = "Availability required!"
            return false
        }

        if (address.text.toString().isEmpty()) {
            address.error = "Address required!"
            return false
        }

        return true
    }
}