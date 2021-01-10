package com.example.ustoyou.model

import com.example.ustoyou.R

class DeliveryServices {
    fun getDeliveryServices(): ArrayList<GenericService>{
        return arrayListOf(
            GenericService(
                R.drawable.delivery_service_1,
                "Pizza Delivery",
                "Best pizza in town. Take you perfect pizza now!\nDelivery time: 50 minutes",
                10
            ), GenericService(
                R.drawable.delivery_service_2,
                "Sushi Delivery",
                "Fresh and 5-stars sushi. Just from here!\n" +
                        "Delivery time: 60 minutes",
                31
            ), GenericService(
                R.drawable.delivery_service_3,
                "Shoes Delivery",
                "Tell me what colour and I'll get you the perfect pair of shoes!\n" +
                        "Delivery time: 100 minutes",
                25
            )
        )
    }
}