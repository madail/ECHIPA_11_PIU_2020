package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.adapters.CornHarvestProviderAdapter
import com.example.ustoyou.adapters.WoodCuttingProviderAdapter
import com.example.ustoyou.model.CornHarvestProviders
import com.example.ustoyou.model.WoodCuttingProviders

class WoodCuttingProvidersList : AppCompatActivity(), AdapterView.OnItemClickListener {

    var woodCuttingProviderAdapter : WoodCuttingProviderAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_providers_list)

        val listReference = findViewById<ListView>(R.id.providers_list)
        listReference.visibility = View.VISIBLE

        woodCuttingProviderAdapter = WoodCuttingProviderAdapter(this@WoodCuttingProvidersList, WoodCuttingProviders().getWoodCuttingProviders())
        listReference.adapter = woodCuttingProviderAdapter

        listReference.setOnItemClickListener(this@WoodCuttingProvidersList)

    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val intent  = Intent(this, WoodCutActivity::class.java)
        startActivity(intent)
    }


}