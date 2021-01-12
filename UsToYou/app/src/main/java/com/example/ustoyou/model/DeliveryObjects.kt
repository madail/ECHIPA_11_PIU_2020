package com.example.ustoyou.model

import com.example.ustoyou.R

class DeliveryObjects {
    companion object {
        var objects: ArrayList<DeliveryObject> = ArrayList()
        fun getObjects(type: Int) {
            this.objects =
                when (type) {
                    0 -> {
                        arrayListOf(
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
                    1 -> {
                        arrayListOf(
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
                    2 -> {
                        arrayListOf(
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
                    else -> ArrayList()
                }
        }

        fun setIsChosen(name: String, isChosen: Boolean) {
            for(obj in objects){
                if(obj.objectName == name){
                    obj.isChosen = isChosen
                    break
                }
            }
        }
    }
}