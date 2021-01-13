package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import com.example.ustoyou.model.Service
import com.example.ustoyou.model.ServicesListSingleton

class TherapyServiceForm1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_therapy_service_form1)
        supportActionBar?.title = "New therapy service"
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radioFaceToFace ->
                    if (checked) {

                    }
                R.id.radioVirtual ->
                    if (checked) {
                        // Ninjas rule
                    }
            }
        }
    }

    fun validate(name: EditText, description: EditText) : Boolean {
        if(name.text.toString() == "") {
            name.error = "Title missing!"
            return false
        }
        if(description.text.toString() == "") {
            description.error = "Description missing!"
            return false;
        }
        return true;
    }

    fun continueToNextActivity(view: View) {
        val name = findViewById<EditText>(R.id.therapyServiceTitleEditText)
        val description = findViewById<EditText>(R.id.therapyServiceDescriptionEditText)
        val category = "Therapy";
        val isValid = validate(name, description)
        if(isValid) {
            if (name.text.toString() != "") {
                val service = Service(name.text.toString(), category, R.drawable.therapy_service_1)
                ServicesListSingleton.services.add(service)
            }
            val intent = Intent(this, TherapyServiceForm2Activity::class.java)
            startActivity(intent)
        }
    }
}