package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.adapters.CornHarvestProviderAdapter
import com.example.ustoyou.model.CornHarvestProviders

class CornHarvestProvidersList : AppCompatActivity(), AdapterView.OnItemClickListener {

    var cornHarvestProviderAdapter : CornHarvestProviderAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_providers_list)

        val listReference = findViewById<ListView>(R.id.providers_list)
        listReference.visibility = View.VISIBLE

        cornHarvestProviderAdapter = CornHarvestProviderAdapter(this@CornHarvestProvidersList, CornHarvestProviders().getCornHarvestProviders())
        listReference.adapter = cornHarvestProviderAdapter

         listReference.setOnItemClickListener(this@CornHarvestProvidersList)

        }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
       val intent  = Intent(this, HarvestActivity::class.java)
        startActivity(intent)
    }


}