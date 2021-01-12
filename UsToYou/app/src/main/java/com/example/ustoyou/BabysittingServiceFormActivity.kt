package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.ustoyou.model.Service
import com.example.ustoyou.model.ServicesListSingleton

class BabysittingServiceFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_babysitting_service_form)

        supportActionBar?.title = "New babysitting service"
    }

    fun continueToNextActivity(view: View) {
        val name = findViewById<EditText>(R.id.babysittingServiceTitleEditText).text.toString()
        val category = "Babysitting";
        val service = Service(name, category)
        ServicesListSingleton.services.add(service)
        val intent = Intent(this, UploadFileActivity::class.java)
        startActivity(intent)
    }
}