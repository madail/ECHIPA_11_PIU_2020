package com.example.ustoyou.model

class User(var firstName: String, var lastName: String, var phoneNumber: String, var email: String, var birthday: String, var cash:Boolean = false) {
    companion object{
        var currentUser: User? = null

        fun setUser(user: User){
            currentUser = user
        }
    }
}