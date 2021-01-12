package com.example.ustoyou.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.example.ustoyou.R
import com.example.ustoyou.model.DeliveryObject
import com.example.ustoyou.model.DeliveryObjects

class DeliveryAdapter(
    private var services: ArrayList<DeliveryObject>,
    var context: Context
) :
    RecyclerView.Adapter<DeliveryAdapter.ViewHolder>() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.pizza_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(services[position], context)
    }

    override fun getItemCount(): Int {
        return services.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: DeliveryObject, context: Context) {
            val title: TextView = itemView.findViewById(R.id.pizzaTitleItem)
            val image: ImageView = itemView.findViewById(R.id.pizzaServiceImage)
            val ingredients: TextView = itemView.findViewById(R.id.pizzaIngredientsItem)
            val toggleButton: ToggleButton = itemView.findViewById(R.id.pizzaDeliveryToggle)
            val price: TextView = itemView.findViewById(R.id.pizzaPriceItem)
            val activity = context as Activity

            image.setImageResource(item.imageRes)
            title.text = item.objectName
            ingredients.text = item.description
            price.text = "Price: ${item.price}$"

            if(activity.intent.getBooleanExtra("confirmation",false)){
                toggleButton.isClickable = false
                toggleButton.isFocusable = false
            }

            toggleButton.isChecked = item.isChosen

            toggleButton.setOnCheckedChangeListener { _, b ->
                DeliveryObjects.setIsChosen(item.objectName, b)
                if (b) {
                    val price: TextView = activity.findViewById(R.id.pizzaDeliveryConfirmationTotal)
                    val currentPrice = price.text.toString().split(" ").toTypedArray()
                    val total = item.price + currentPrice[1].dropLast(1).toInt()
                    price.text = "Total: ${total}$"
                } else {
                    val activity = context as Activity
                    val price: TextView = activity.findViewById(R.id.pizzaDeliveryConfirmationTotal)
                    val currentPrice = price.text.toString().split(" ").toTypedArray()
                    val total = currentPrice[1].dropLast(1).toInt() - item.price
                    price.text = "Total: ${total}$"
                }
            }

        }

    }
}