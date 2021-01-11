package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ConfirmServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_service)
    }

    override fun onBackPressed() {
        val intent = Intent(this, ProfilePageActivity::class.java)
        startActivity(intent)
    }
}