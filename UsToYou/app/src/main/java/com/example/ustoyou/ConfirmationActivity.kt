package com.example.ustoyou

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ustoyou.model.Order
import com.example.ustoyou.model.Orders


class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
    }

    fun home(view: View) {
        val order = intent.getSerializableExtra("order") as Order
        Orders.addOrder(order)
        val intent = Intent(this, MyOrdersActivity::class.java)
        startActivity(intent)
    }
}