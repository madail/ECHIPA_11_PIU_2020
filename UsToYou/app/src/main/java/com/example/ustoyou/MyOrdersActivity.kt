package com.example.ustoyou

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ustoyou.adapters.MyOrdersAdapter
import com.example.ustoyou.model.Orders

class MyOrdersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)

        val recyclerView: RecyclerView = findViewById(R.id.rvMyOrders)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        val adapter = MyOrdersAdapter(
            Orders.orders,
            this,
        )
        supportActionBar?.title = "My Orders"

        recyclerView.adapter = adapter
    }
}