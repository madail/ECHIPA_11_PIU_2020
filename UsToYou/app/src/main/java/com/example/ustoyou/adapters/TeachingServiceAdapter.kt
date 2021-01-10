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
import com.example.ustoyou.TeachingServiceDetails
import com.example.ustoyou.model.TeachingService

class TeachingServiceAdapter(
    var teachingServices: ArrayList<TeachingService>,
    var context: Context
) :
    RecyclerView.Adapter<TeachingServiceAdapter.ViewHolder>() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.teaching_service_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(teachingServices[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, TeachingServiceDetails::class.java)
            intent.putExtra("selectedTeachingService", teachingServices[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return teachingServices.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: TeachingService) {
            val title: TextView = itemView.findViewById(R.id.titleTeachingItem)
            val image: ImageView = itemView.findViewById(R.id.teachingServiceImage)

            image.setImageResource(item.imageRes)
            title.text = item.titleSubject
        }

    }
}