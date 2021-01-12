package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
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

    fun continueToNextActivity(view: View) {
        val name = findViewById<EditText>(R.id.therapyServiceTitleEditText).text.toString()
        val category = "Therapy";
        if( name != "") {
            val service = Service(name, category)
            ServicesListSingleton.services.add(service)
        }
        val intent = Intent(this, TherapyServiceForm2Activity::class.java)
        startActivity(intent)
    }
}