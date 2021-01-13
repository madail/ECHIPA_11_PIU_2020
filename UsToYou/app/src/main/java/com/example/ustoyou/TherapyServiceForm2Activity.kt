package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.ustoyou.model.GenericService

class TherapyServiceForm2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_therapy_service_form2)
        supportActionBar?.title = "New therapy service"
    }

    fun validate(price: EditText, city: EditText, phone: EditText, email: EditText) : Boolean {
        if(price.text.toString() == "") {
            price.error = "Price missing!"
            return false
        }
        if(city.text.toString() == "") {
            city.error = "City missing!"
            return false
        }
        if(phone.text.toString() == "") {
            phone.error = "Phone missing!"
            return false
        }
        if(email.text.toString() == "") {
            email.error = "E-mail missing!"
            return false
        }
        return true;
    }

    fun continueToNextActivity(view:View) {
        val price = findViewById<EditText>(R.id.therapyServiceNameEditText)
        val city = findViewById<EditText>(R.id.therapyServiceCityEditText)
        val phone = findViewById<EditText>(R.id.therapyServicePhoneEditText)
        val email = findViewById<EditText>(R.id.therapyServiceEmailEditText)
        val isValid = validate(price, city, phone, email)
        if(isValid) {
            val intent = Intent(this, ConfirmServiceActivity::class.java)
            startActivity(intent)
        }
    }
}