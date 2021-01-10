package com.example.ustoyou.model

import com.example.ustoyou.R

class TeachingServices {
    fun getTeachingServices(): ArrayList<TeachingService> {
        return arrayListOf(
            TeachingService(
                R.drawable.teaching_service_1,
                "English Teacher",
                "Good teacher with a lot of experience, looking for students eager to learn english.",
                30
            ), TeachingService(
                R.drawable.teaching_service_2,
                "Math Teacher",
                "Good teacher with a lot of experience, looking for students eager to learn math.",
                35
            ), TeachingService(
                R.drawable.teaching_service_3,
                "Dance Teacher",
                "Good teacher with a lot of experience, looking for students eager to learn how to dance like a butterfly.",
                23
            ), TeachingService(
                R.drawable.teaching_service_4,
                "Public Speaking Teacher",
                "Good teacher with a lot of experience, looking for students eager to speak in public.",
                70
            )
        )
    }
}