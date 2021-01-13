package com.example.ustoyou.VisualAid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ustoyou.R

class VisualAidActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visual_aid)

        supportActionBar?.title = "Us to You"
    }

    fun endCall(view: View) {

    }

    fun toggleMicrophone(view: View) {

    }
}