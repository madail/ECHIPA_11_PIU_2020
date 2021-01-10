package com.example.ustoyou

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ustoyou.adapters.TeachingServiceAdapter
import com.example.ustoyou.model.TeachingServices

class TeachingServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teaching_service)

        val recyclerView: RecyclerView = findViewById(R.id.rvTeachingServices)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        val adapter = TeachingServiceAdapter(
            TeachingServices().getTeachingServices(),
            this
        )
        supportActionBar?.title = "Teaching Services"

        recyclerView.adapter = adapter
    }
}