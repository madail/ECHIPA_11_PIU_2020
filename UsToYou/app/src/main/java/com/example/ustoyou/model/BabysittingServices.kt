package com.example.ustoyou.model

import com.example.ustoyou.R

class BabysittingServices {
    fun getBabysittingServices(): ArrayList<GenericService> {
        return arrayListOf(
            GenericService(
                R.drawable.babysitting_service_1,
                "Emily Peterson",
                "I love kids and I have a lot of experience in take care of them. I have seven brothers and I'm the oldest one.",
                10
            ), GenericService(
                R.drawable.babysitting_service_2,
                "Marshall Erickson",
                "I have a lots of diplomas for taking care of children. I can teach your kid how to count in spanish and how to dance like a penguin.",
                12
            ), GenericService(
                R.drawable.babysitting_service_3,
                "James Paddington",
                "I like to take care of children of all ages. I have been a nanny since 2003 and every family has been very grateful.",
                15
            )
        )
    }
}
