package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CategoryActivity : AppCompatActivity() {

    var selectedPosition: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        supportActionBar?.title = "Create a new service"

        val categories = resources.getStringArray(R.array.category_list)

        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedPosition = position;
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    fun launchNextActivity(view: View) {
        if(selectedPosition == 0) {
            val intent = Intent(this, TherapyServiceForm1Activity::class.java)
            startActivity(intent)
        }
        if(selectedPosition == 1) {
            val intent = Intent(this, BabysittingServiceFormActivity::class.java)
            startActivity(intent)
        }
        if(selectedPosition == 2) {
            val intent = Intent(this, DeliveryServiceFormActivity::class.java)
            startActivity(intent)
        }
    }
}