package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class YourTeachingServiceConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_teaching_service_confirmation)
    }

    fun order(view: View) {
        val intent = Intent(this, ConfirmationActivity::class.java)
        startActivity(intent)
    }
    fun back(view: View) {
        val intent = Intent(this, TeachingServiceDetails::class.java)
        startActivity(intent)
    }
}