package com.example.ustoyou.model

import com.example.ustoyou.mocks.EmptyMock
import com.example.ustoyou.mocks.IMockOrder
import java.io.Serializable

data class Order(var category: String, var orderName: String, var imageRes: Int, var details: IMockOrder = EmptyMock()): Serializable