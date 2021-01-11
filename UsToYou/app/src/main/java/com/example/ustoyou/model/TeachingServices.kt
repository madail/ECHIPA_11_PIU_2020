package com.example.ustoyou.model

import com.example.ustoyou.R

class TeachingServices {
    fun getTeachingServices(): ArrayList<GenericService> {
        return arrayListOf(
            GenericService(
                R.drawable.teaching_service_2,
                "English Teacher",
                "Good teacher with a lot of experience, looking for students eager to learn english.",
                30
            ), GenericService(
                R.drawable.teaching_service_4,
                "Math Teacher",
                "Good teacher with a lot of experience, looking for students eager to learn math.",
                35
            ), GenericService(
                R.drawable.teaching_service_3,
                "Dance Teacher",
                "Good teacher with a lot of experience, looking for students eager to learn how to dance like a butterfly.",
                23
            ), GenericService(
                R.drawable.teaching_service_1,
                "Public Speaking Teacher",
                "Good teacher with a lot of experience, looking for students eager to speak in public.",
                70
            )
        )
    }
}