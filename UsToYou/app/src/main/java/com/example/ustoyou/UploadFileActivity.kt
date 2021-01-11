package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class UploadFileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_file)
        supportActionBar?.title = "New babysitting service"

        //TO DO: CRISTINA UPLOAD FILE
    }

    fun continueToConfirmation(view: View) {
        val intent = Intent(this, ConfirmServiceActivity::class.java)
        startActivity(intent)
    }
}