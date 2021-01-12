package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.adapters.CornHarvestProviderAdapter
import com.example.ustoyou.adapters.ITProviderAdapter
import com.example.ustoyou.model.CornHarvestProviders
import com.example.ustoyou.model.ITProviders
import com.example.ustoyou.model.Order

class ITProvidersList : AppCompatActivity(), AdapterView.OnItemClickListener {

    var itProviderAdapter : ITProviderAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_providers_list)

        val listReference = findViewById<ListView>(R.id.providers_list)
        listReference.visibility = View.VISIBLE

        itProviderAdapter = ITProviderAdapter(this@ITProvidersList, ITProviders().getITProviders())
        listReference.adapter = itProviderAdapter

        listReference.setOnItemClickListener(this@ITProvidersList)

    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val nameText = p1?.findViewById<TextView>(R.id.provider_it_item_name)
        val address =  p1?.findViewById<TextView>(R.id.provider_it_item_address)
        val price =  p1?.findViewById<TextView>(R.id.provider_it_item_price)

        val Price= price?.text.toString().split(" ")[1]
        val intent  = Intent(this, ITSupportActivity::class.java).apply {
            if (nameText != null) {
                putExtra("name",nameText.text.toString())
            }
            if (address != null) {
                putExtra("address", address.text.toString())
            }
            putExtra("price", Price.toInt())
        }
        startActivity(intent)
    }


}