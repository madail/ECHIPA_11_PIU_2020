package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.ITConfirmationDetails
import com.example.ustoyou.payment.PaymentMethodActivity

class ITSupportActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_it_support_info)


    }

    fun goToPaymentMethod(view: View) {
        val name = intent.getStringExtra("name")
        val address = intent.getStringExtra("address")
        val price: Int = intent.getIntExtra("price", -1)

        val problem = findViewById<EditText>(R.id.it_support_problemEditText)
        val availability = findViewById<EditText>(R.id.it_support_availabilityEditText)
        val teamViewerId = findViewById<EditText>(R.id.it_support_idEditText)
        val teamViewerPassword = findViewById<EditText>(R.id.it_support_passwordEditText)

        val itConfirmationDetails= ITConfirmationDetails(
            name.toString().substring(6),
            address.toString().substring(9),
            price,
            availability.text.toString(),
            teamViewerId.text.toString(),
            teamViewerPassword.text.toString()
        )


        val isValid = validateDetails(
            problem,
            availability,
            teamViewerId,
            teamViewerPassword
        )

        if (isValid) {

            val intent1 = Intent(this, PaymentMethodActivity::class.java).apply {
                putExtra("ITInfo", itConfirmationDetails)
                putExtra("activity", "IT")
            }
            startActivity(intent1)
        }
    }

    private fun validateDetails(
        problem: EditText,
        availability: EditText,
        teamViewerId: EditText,
        teamViewerPassword: EditText
    ): Boolean {
        if (problem.text.toString().isEmpty()) {
            problem.error = "Problem's nature required!"
            return false
        }

        if (availability.text.toString().isEmpty()) {
            availability.error = "Availability required!"
            return false
        }

        if (teamViewerId.text.toString().isEmpty()) {
            teamViewerId.error = "Team Viewer Id required!"
            return false
        }

        if (teamViewerPassword.text.toString().isEmpty()) {
            teamViewerPassword.error = "Team Viewer Password required!"
            return false
        }

        return true
    }
}