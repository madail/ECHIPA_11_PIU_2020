package com.example.ustoyou.model

import com.example.ustoyou.R

class TherapyServices {
    companion object{
        var therapyServices = arrayListOf(
            GenericService(
                R.drawable.therapy_service_1,
                "Lilly Walker",
                "My mission is to help people",
                20
            )
        )

        fun addServices(genericService: GenericService){
            therapyServices.add(genericService)
        }
    }
}