package com.example.vladislav.lovefood.models

data class Order (
        val id: Int,
        val nameClient: String,
        val address: String,
        val phone: String,
        val date: String,
        val idFood: String

){
    class List : ArrayList<Order>()
}
