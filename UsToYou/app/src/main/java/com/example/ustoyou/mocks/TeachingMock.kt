package com.example.ustoyou.mocks

class TeachingMock : IMockOrder {
    private var teacherName: String = "John Doe"
    private var date: String = "14/01/2021 14:30"
    private var type: String = "Virtual"
    private var paymentType: String = "Credit Card"
    private var lessonFinished: Boolean = false
    private var showLink: Boolean = true

    private object INSTANCE {
        val INSTANCE = TeachingMock()
    }

    companion object {
        val instance: TeachingMock by lazy { INSTANCE.INSTANCE }
    }

}