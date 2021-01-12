package com.example.ustoyou.model

data class DeliveryObject(
    var imageRes: Int,
    var description: String,
    var objectName: String,
    var price: Int,
    var isChosen: Boolean
) {
}