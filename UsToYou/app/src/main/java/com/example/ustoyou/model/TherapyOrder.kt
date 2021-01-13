package com.example.ustoyou.model

import java.io.Serializable

class TherapyOrder(
    var name: String,
    var phone: String,
    var address: String,
    var sessionType: String,
    var date: String,
    var providerName: String,
) : Serializable {
    companion object {
        lateinit var orders: TherapyOrder

        fun addOrder(order: TherapyOrder) {
            orders = order
        }
    }
}