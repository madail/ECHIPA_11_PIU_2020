package com.example.ustoyou.model

import java.io.Serializable

data class Order(var category: String, var orderName: String, var imageRes: Int, var date: String = "", var type: String = "", var paymentType: String = ""): Serializable