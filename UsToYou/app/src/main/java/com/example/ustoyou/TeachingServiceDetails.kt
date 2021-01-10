package com.example.ustoyou

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class TeachingServiceDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teaching_service_details)
        supportActionBar?.title = "English Teacher"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    fun orderNow(view: View) {
        //TODO
    }
}