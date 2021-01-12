package com.example.ustoyou

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.Service
import com.example.ustoyou.model.ServicesListSingleton
import com.squareup.picasso.Picasso


class DeliveryServiceFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_service_form)
        supportActionBar?.title = "New delivery service"

    }

    fun continueToConfirmation(view : View) {
        val name = findViewById<EditText>(R.id.deliveryServiceTitleEditText).text.toString()
        val category = "Delivery";
        val service = Service(name, category)
        ServicesListSingleton.services.add(service)
        val intent = Intent(this, UploadPhotoActivity::class.java)
        startActivity(intent)
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radioDrone ->
                    if (checked) {

                    }
                R.id.radioCar ->
                    if (checked) {
                        // Ninjas rule
                    }
            }
        }
    }



}