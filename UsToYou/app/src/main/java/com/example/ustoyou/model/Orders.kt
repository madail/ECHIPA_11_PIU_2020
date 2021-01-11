package com.example.ustoyou.model

class Orders() {
    companion object {
        var orders: ArrayList<Order> = ArrayList();

        fun addOrder(order: Order) {
            orders.add(order)
        }
    }

}