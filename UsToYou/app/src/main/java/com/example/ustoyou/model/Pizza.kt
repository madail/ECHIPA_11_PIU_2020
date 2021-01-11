package com.example.ustoyou.model

data class Pizza(
    var imageRes: Int,
    var ingredients: String,
    var pizzaName: String,
    var price: Int,
    var isChosen: Boolean
) {
}