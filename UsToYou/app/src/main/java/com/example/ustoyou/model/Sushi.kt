package com.example.ustoyou.model

import com.example.ustoyou.R

class Sushi {
    fun getSushi(): ArrayList<DeliveryObject>{
        return arrayListOf(
            DeliveryObject(
                R.drawable.sushi_service_1,
                "Rice, Salmon",
                "Nigiri Roll",
                25,
                false
            ), DeliveryObject(
                R.drawable.sushi_service_2,
                "Tamago, Cucumber, Rice",
                "Maki Roll",
                21,
                false
            ), DeliveryObject(
                R.drawable.sushi_service_3,
                "Rice, Alga Nori, Chashu",
                "Chashu Roll",
                23,
                false
            )
        )
    }
}