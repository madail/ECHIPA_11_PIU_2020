package com.example.ustoyou.model

import com.example.ustoyou.R

class Shoes {
    fun getShoes(): ArrayList<DeliveryObject> {
        return arrayListOf(
            DeliveryObject(
                R.drawable.shoes_service_1,
                "Size: 39",
                "Adidas",
                25,
                false
            ), DeliveryObject(
                R.drawable.shoes_service_2,
                "Size: 38",
                "Ball Shoes",
                21,
                false
            ), DeliveryObject(
                R.drawable.shoes_service_3,
                "Size: 35",
                "Vans",
                23,
                false
            )
        )
    }
}