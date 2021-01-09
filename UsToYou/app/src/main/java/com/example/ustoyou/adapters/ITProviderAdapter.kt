package com.example.ustoyou.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.ustoyou.R
import com.example.ustoyou.model.ITProvider
import com.example.ustoyou.model.Provider

class ITProviderAdapter (private val context : Context, private var providers : ArrayList<ITProvider>) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return providers.size
    }

    override fun getItem(id: Int): Any {
        return providers[id]
    }

    override fun getItemId(id: Int): Long {
        return id.toLong()
    }

    override fun getView(position: Int, convertView: View?, viewGroupParent: ViewGroup?): View {
        val item = inflater.inflate(R.layout.activity_it_provider_item, viewGroupParent, false)

        val imageView = item.findViewById<ImageView>(R.id.provider_it_item_imageView)
        val nameText = item.findViewById<TextView>(R.id.provider_it_item_name)
        val address = item.findViewById<TextView>(R.id.provider_it_item_address)
        val price = item.findViewById<TextView>(R.id.provider_it_item_price)
        val qualification = item.findViewById<TextView>(R.id.provider_it_item_qualification)

        nameText.text ="Name: " + providers[position].name
        imageView.setImageResource(providers[position].img)
        price.text = "Price: " + providers[position].price.toString() + " RON/H"
        address.text = "Address: " + providers[position].address
        qualification.text = "Qualification: " + providers[position].qualification


        return item
    }


}