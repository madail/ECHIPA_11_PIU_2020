package com.example.ustoyou.model

import com.example.ustoyou.R
import com.example.ustoyou.mocks.TeachingMock

class Orders() {
    companion object {
        var orders: ArrayList<Order> = ArrayList();

        init {
            orders.add(0, Order("teaching", "Demo Virtual Lesson",
                R.drawable.teaching_service_1, TeachingMock.instance))
        }

        fun addOrder(order: Order) {
            orders.add(order)
        }
    }

}