package com.example.ustoyou.model

import java.io.Serializable

data class GenericService(
    var imageRes: Int,
    var titleSubject: String,
    var description: String,
    var price: Int
): Serializable