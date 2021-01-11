package com.example.ustoyou.model

import com.example.ustoyou.R

class Pizzas {
    fun getPizzas(): ArrayList<DeliveryObject>{
        return arrayListOf(
            DeliveryObject(
                R.drawable.pizza_service_1,
                "Corn, Cheese, Tomato",
                "Vegetarian Pizza",
                25,
                false
            ), DeliveryObject(
                R.drawable.pizza_service_2,
                "Eggs, Olives, Cheese, Bacon",
                "Egg Pizza",
                21,
                false
            ), DeliveryObject(
                R.drawable.pizza_service_3,
                "Salami, Cheese",
                "Diavola Pizza",
                23,
                false
            )
        )
    }
}