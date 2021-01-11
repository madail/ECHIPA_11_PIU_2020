package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class BabysittingServiceFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_babysitting_service_form)

        supportActionBar?.title = "New babysitting service"
    }

    fun continueToNextActivity(view: View) {
        val intent = Intent(this, UploadFileActivity::class.java)
        startActivity(intent)
    }
}