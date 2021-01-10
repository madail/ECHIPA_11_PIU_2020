package com.example.ustoyou.model

import java.io.Serializable

data class TeachingServiceOrderDetails(
    var name: String,
    var phone: String,
    var type: String,
    var address: String,
    var date: String
) : Serializable {
}