package com.example.ustoyou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ustoyou.adapters.TeachingServiceAdapter
import com.example.ustoyou.model.DeliveryServices
import com.example.ustoyou.model.TeachingServices

class DeliveryServicesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_services)

        val recyclerView: RecyclerView = findViewById(R.id.rvDeliveryServices)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        val adapter = TeachingServiceAdapter(
            DeliveryServices().getDeliveryServices(),
            this,
            "delivery"
        )
        supportActionBar?.title = "Delivery Services"

        recyclerView.adapter = adapter
    }
}