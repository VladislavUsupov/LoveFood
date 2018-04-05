package com.example.vladislav.lovefood.models

data class Food (
        val id: String,
        val nameFood: String,
        val price: String,
        val imageUrl: String,
        val idRestaurant: String

){
    class List : ArrayList<Food>()
}
