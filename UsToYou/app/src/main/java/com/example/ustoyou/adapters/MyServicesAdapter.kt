package com.example.ustoyou.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.ustoyou.R
import com.example.ustoyou.model.Service

class MyServicesAdapter(private val context : Context, private val serviceList: ArrayList<Service>) : BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("SetTextI18n", "ViewHolder")
    override fun getView(position: Int, containerView: View?, viewGroupParent: ViewGroup?): View {
        val item = inflater.inflate(R.layout.service_list_item, viewGroupParent, false)
        val serviceName = item.findViewById<TextView>(R.id.serviceName)
        val serviceCategory = item.findViewById<TextView>(R.id.serviceCategory)

        serviceName.text = serviceList[position].name
        serviceCategory.text = "Category: " + serviceList[position].category

        return item
    }

    override fun getItem(id: Int): Any {
        return serviceList[id]
    }

    override fun getItemId(id: Int): Long {
        return id.toLong()
    }

    override fun getCount(): Int {
        return serviceList.size
    }
}