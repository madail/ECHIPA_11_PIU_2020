package com.example.ustoyou

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
    }

    fun home(view: View) {

        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
    }
}