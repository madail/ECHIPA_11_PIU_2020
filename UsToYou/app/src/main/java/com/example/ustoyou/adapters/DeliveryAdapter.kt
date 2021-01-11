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

class DeliveryAdapter(
    var pizzaServices: ArrayList<DeliveryObject>,
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
        holder.bind(pizzaServices[position], context)
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return pizzaServices.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: DeliveryObject, context: Context) {
            val title: TextView = itemView.findViewById(R.id.pizzaTitleItem)
            val image: ImageView = itemView.findViewById(R.id.pizzaServiceImage)
            val ingredients: TextView = itemView.findViewById(R.id.pizzaIngredientsItem)
            val toggleButton: ToggleButton = itemView.findViewById(R.id.pizzaDeliveryToggle)
            val price: TextView = itemView.findViewById(R.id.pizzaPriceItem)

            image.setImageResource(item.imageRes)
            title.text = item.pizzaName
            ingredients.text = item.ingredients
            item.isChosen = toggleButton.isChecked
            price.text = "Price: ${item.price}$"

            toggleButton.setOnCheckedChangeListener { _, b ->
                if (b) {
                    val activity = context as Activity
                    val price: TextView = activity.findViewById(R.id.pizzaDeliveryTotal)
                    val currentPrice = price.text.toString().split(" ").toTypedArray()
                    val total = item.price + currentPrice[1].dropLast(1).toInt()
                    price.text = "Total: ${total}$"
                } else {
                    val activity = context as Activity
                    val price: TextView = activity.findViewById(R.id.pizzaDeliveryTotal)
                    val currentPrice = price.text.toString().split(" ").toTypedArray()
                    val total = currentPrice[1].dropLast(1).toInt() - item.price
                    price.text = "Total: ${total}$"
                }
            }

        }

    }
}