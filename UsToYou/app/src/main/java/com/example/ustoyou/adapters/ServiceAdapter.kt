package com.example.ustoyou.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ustoyou.R
import com.example.ustoyou.ServiceDetails
import com.example.ustoyou.model.GenericService

class ServiceAdapter(
    var genericServices: ArrayList<GenericService>,
    var context: Context,
    var type: String
) :
    RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.service_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genericServices[position])
        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(holder.itemView.context, ServiceDetails::class.java)
            intent.putExtra("selectedTeachingService", genericServices[position])
            intent.putExtra("type", type)
            intent.putExtra("typeOfDelivery",position)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return genericServices.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: GenericService) {
            val title: TextView = itemView.findViewById(R.id.titleTeachingItem)
            val image: ImageView = itemView.findViewById(R.id.teachingServiceImage)

            image.setImageResource(item.imageRes)
            title.text = item.titleSubject
        }

    }
}