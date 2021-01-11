package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.model.GenericService

class TeachingServiceDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teaching_service_details)

        val teachingService =
            intent.getSerializableExtra("selectedTeachingService") as GenericService

        supportActionBar?.title = teachingService.titleSubject

        val imageView: ImageView = findViewById(R.id.teachingServiceImage)
        imageView.setImageResource(teachingService.imageRes)

        val descriptionText: TextView = findViewById(R.id.descriptionTeachingService)
        descriptionText.text = teachingService.description

        val priceText: TextView = findViewById(R.id.priceValueTeachingService)

        if (intent.getStringExtra("type") == "delivery") {
            val price: TextView = findViewById(R.id.priceTeachingService)
            priceText.visibility = View.GONE
            price.visibility = View.GONE
        } else {
            priceText.text = "${teachingService.price}$"
        }

        val titleText: TextView = findViewById(R.id.titleTeachingSubject)
        titleText.text = teachingService.titleSubject
    }

    fun orderNow(view: View) {
        val type = intent.getStringExtra("type")
        val teachingService =
            intent.getSerializableExtra("selectedTeachingService") as GenericService
        if (type == "babysitting") {
            val intent = Intent(this, YourOrderBabysittingActivity::class.java)
            intent.putExtra("image",teachingService.imageRes)
            startActivity(intent)
        } else if (type == "delivery") {
            val intent = Intent(this, YourPizzaDeliveryActivity::class.java)
            intent.putExtra("image",teachingService.imageRes)
            intent.putExtra("name",teachingService.titleSubject)
            startActivity(intent)
        } else {
            val intent = Intent(this, YourTeachingServiceOrder::class.java)
            intent.putExtra("image",teachingService.imageRes)
            startActivity(intent)
        }
    }
}