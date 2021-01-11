package com.example.ustoyou.model

import java.io.Serializable

class BabysittingOrder(
    var name: String,
    var phone: String,
    var address: String,
    var childsAge: String,
    var date: String
) : Serializable {
}