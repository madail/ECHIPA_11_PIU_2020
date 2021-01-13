package com.example.ustoyou.VisualAid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.example.ustoyou.R

class VisualAidActivity : AppCompatActivity() {
    private var areButtonsVisible: Boolean = false
    private var isWebCamVisible: Boolean = false
    private var isMicrophoneOn: Boolean = false

    private lateinit var visualAidEndCallButton: Button
    private lateinit var visualAidToggleMicrophoneButton: Button
    private lateinit var visualAidWebCamWrapper: LinearLayout
    private lateinit var visualAidDefaultIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visual_aid)

        supportActionBar?.title = "Us to You"

        bindItems()

        initLayout()
    }

    private fun bindItems() {
        visualAidEndCallButton = findViewById(R.id.visualAidEndCallButton)
        visualAidToggleMicrophoneButton = findViewById(R.id.visualAidToggleMicrophoneButton)
        visualAidWebCamWrapper = findViewById(R.id.visualAidWebCamWrapper)
        visualAidDefaultIcon = findViewById(R.id.visualAidDefaultIcon)
    }

    private fun initLayout() {
        visualAidEndCallButton.visibility = View.GONE
        visualAidWebCamWrapper.visibility = View.GONE
        visualAidToggleMicrophoneButton.visibility = View.GONE
    }

    fun endCall(view: View) {

    }

    fun toggleMicrophone(view: View) {
        if (isMicrophoneOn) {
            isMicrophoneOn = false
            Toast.makeText(this@VisualAidActivity, "Microphone off", Toast.LENGTH_SHORT).show()
        } else {
            isMicrophoneOn = true
            Toast.makeText(this@VisualAidActivity, "Microphone on", Toast.LENGTH_SHORT).show()
        }
    }

    fun onDefaultIconClick(view: View) {
        if (!areButtonsVisible) {
            areButtonsVisible = true
            visualAidEndCallButton.visibility = View.VISIBLE
            visualAidToggleMicrophoneButton.visibility = View.VISIBLE

            Toast.makeText(this@VisualAidActivity, "Call started", Toast.LENGTH_SHORT).show()
        } else {
            if (isWebCamVisible) {
                isWebCamVisible = false
                visualAidWebCamWrapper.visibility = View.GONE
                Toast.makeText(this@VisualAidActivity, "Camera off", Toast.LENGTH_SHORT).show()
            } else {
                isWebCamVisible = true
                visualAidWebCamWrapper.visibility = View.VISIBLE
                Toast.makeText(this@VisualAidActivity, "Camera on", Toast.LENGTH_SHORT).show()
            }
        }
    }
}