package com.example.vladislav.lovefood.models

data class Restaurant (
        val id: String,
        val nameRestaurant: String,
        val minOrderAmount: String,
        val deliveryTime: String,
        val imageUrl: String

) {
    class List : ArrayList<Restaurant>()
}
