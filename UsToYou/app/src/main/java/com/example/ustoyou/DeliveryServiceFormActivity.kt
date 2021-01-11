package com.example.ustoyou

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


class DeliveryServiceFormActivity : AppCompatActivity() {

    var image_uri: EditText? = null
    var imageView: ImageView? = null
    private val SELECT_PICTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_service_form)
        supportActionBar?.title = "New delivery service"

        imageView  = findViewById(R.id.image_display);
        image_uri = findViewById(R.id.image_name);
    }

    fun continueToConfirmation(view : View) {
        val intent = Intent(this, ConfirmServiceActivity::class.java)
        startActivity(intent)
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radioDrone ->
                    if (checked) {

                    }
                R.id.radioCar ->
                    if (checked) {
                        // Ninjas rule
                    }
            }
        }
    }

    fun fetchImageFromGallery(view: View?) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                val selectedImageURI: Uri? = data?.data
                image_uri?.setText(selectedImageURI.toString())
                Picasso.with(this@DeliveryServiceFormActivity).load(selectedImageURI).noPlaceholder().centerCrop()
                    .fit()
                    .into(findViewById<View>(R.id.image_display) as ImageView)
            }
        }
    }

}