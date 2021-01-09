package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.adapters.CornHarvestProviderAdapter
import com.example.ustoyou.adapters.ITProviderAdapter
import com.example.ustoyou.model.CornHarvestProviders
import com.example.ustoyou.model.ITProviders

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
        val intent  = Intent(this, ITSupportActivity::class.java)
        startActivity(intent)
    }


}