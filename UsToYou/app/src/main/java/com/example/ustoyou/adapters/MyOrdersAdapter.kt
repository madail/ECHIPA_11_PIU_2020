package com.example.ustoyou.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ustoyou.R
import com.example.ustoyou.model.Order

class MyOrdersAdapter(
    var orders: ArrayList<Order>,
    var context: Context
) :
    RecyclerView.Adapter<MyOrdersAdapter.ViewHolder>() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.my_orders_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Order) {
            val title: TextView = itemView.findViewById(R.id.titleMyOrdersItem)
            val image: ImageView = itemView.findViewById(R.id.myOrdersImage)
            val category: TextView = itemView.findViewById(R.id.categoryMyOrdersItem)

            image.setImageResource(item.imageRes)
            title.text = item.orderName
            category.text = item.category
        }

    }
}