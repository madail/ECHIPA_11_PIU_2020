package com.example.ustoyou

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ustoyou.adapters.ServiceAdapter
import com.example.ustoyou.model.BabysittingServices

class BabySittingServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baby_sitting_service)

        val recyclerView: RecyclerView = findViewById(R.id.rvBabySittingServices)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        val adapter = ServiceAdapter(
            BabysittingServices().getBabysittingServices(),
            this,
            "babysitting"
        )
        supportActionBar?.title = "Babysitting Services"

        recyclerView.adapter = adapter
    }
}