package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.ustoyou.adapters.MyServicesAdapter
import com.example.ustoyou.model.MyServices
import com.example.ustoyou.model.ServicesListSingleton

class MyServicesActivity : AppCompatActivity() {

    private lateinit var listReference : ListView
    var servicesAdapter : MyServicesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_services)

        supportActionBar?.title = "My services"

        listReference = findViewById(R.id.servicesListView)

        servicesAdapter = MyServicesAdapter(this, MyServices().getServices())
        listReference.adapter = servicesAdapter

        if(ServicesListSingleton.services.size != 0) {
            val image = findViewById<ImageView>(R.id.sadService)
            image.visibility = View.GONE
            val text = findViewById<TextView>(R.id.noServiceText)
            text.visibility = View.GONE
            val list = findViewById<ListView>(R.id.servicesListView)
            list.visibility = View.VISIBLE
        }
    }

    fun addService(view: View){
        val intent = Intent(this, CategoryActivity::class.java)
        startActivity(intent)
    }
}