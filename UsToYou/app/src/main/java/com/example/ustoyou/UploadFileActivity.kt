package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class UploadFileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_file)
        supportActionBar?.title = "New babysitting service"

    }

    fun fetchFiles(view:View) {
        Toast.makeText(this, "Successfully uploaded resume!", Toast.LENGTH_LONG).show()
    }

    fun continueToConfirmation(view: View) {
        val intent = Intent(this, ConfirmServiceActivity::class.java)
        startActivity(intent)
    }
}