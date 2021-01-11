package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.Order

class PaymentMethodActivity : AppCompatActivity() {

    lateinit var spinner: Spinner
    private val currency = arrayOf<String>("RON", "GBP", "EUR", "USD")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)

        spinner = findViewById(R.id.payment_method_spinner)

        val currencyAdapter = ArrayAdapter(
            this, R.layout.support_simple_spinner_dropdown_item, currency
        )
        currencyAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = currencyAdapter
    }

    fun confirm(view: View) {
        val teachingServiceOrderDetails = intent.getSerializableExtra("teachingOrder")
        val babysittingOrder = intent.getSerializableExtra("babySittingOrder")
        val pizza = intent.getStringExtra("pizza")
        val image: Int = intent.getIntExtra("image", -1)

        val radioGroup: RadioGroup = findViewById(R.id.payMethodRadioButtons)
        val radioButtonSelected = resources.getResourceEntryName(radioGroup.checkedRadioButtonId)
        Log.d("PAYMENT", radioButtonSelected)

        var intent1: Intent = Intent()
        if (teachingServiceOrderDetails == null && pizza != "pizza") {
            intent1 = Intent(this, YourOrderBabysittingConfirmation::class.java)
        } else if (babysittingOrder == null && pizza != "pizza") {
            intent1 = Intent(this, YourTeachingServiceConfirmation::class.java)
        } else if (pizza == "pizza") {
            intent1 = Intent(this, ConfirmationActivity::class.java)
            var name = intent.getStringExtra("name")
            if (name == null) {
                name = ""
            }
            val order = Order("Delivery", name, intent.getIntExtra("image", -1))
            intent1.putExtra("order", order)
        }

        intent1.putExtra("teachingOrder", teachingServiceOrderDetails)
        intent1.putExtra("babySittingOrder", babysittingOrder)
        intent1.putExtra("image", image)

        if (radioButtonSelected == "payment_method_cash") {
            intent1.putExtra("cash", true)
        }

        startActivity(intent1)
    }


    fun goToPayment(view: View) {
        val cashPayment: RadioButton = findViewById(R.id.payment_method_cash)
        val cardPayment: RadioButton = findViewById(R.id.payment_method_card)
        if (cashPayment.isChecked) {
            val intent = Intent(this, ConfirmationActivity::class.java)
            startActivity(intent)
        }

    }
}