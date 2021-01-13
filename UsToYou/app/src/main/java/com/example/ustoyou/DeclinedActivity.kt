package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.babysitting.BabySittingServiceActivity
import com.example.ustoyou.delivery.DeliveryServicesActivity
import com.example.ustoyou.teaching.TeachingServiceActivity


class DeclinedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_declined)
    }

    fun payment(view: View) {
        var intent1 = Intent()
        when (intent.getStringExtra("activity")) {
            "babysitting" -> intent1 = Intent(this, BabySittingServiceActivity::class.java)
            "delivery" -> intent1 = Intent(this, DeliveryServicesActivity::class.java)
            "teaching" -> intent1 = Intent(this, TeachingServiceActivity::class.java)
        }
        startActivity(intent1)
    }
}