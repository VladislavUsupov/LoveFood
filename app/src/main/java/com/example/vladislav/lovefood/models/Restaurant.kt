package com.example.vladislav.lovefood.models

import com.example.vladislav.lovefood.R

data class Restaurant (val nameRestaurant: String, val minOrderAmount: String, val deliveryTime: String, val imageRestaurant: Int){

     companion object {

         val restaurants: ArrayList<Restaurant> = generateRestaurant()

         private fun generateRestaurant(): ArrayList<Restaurant> {

             var restaurants = ArrayList<Restaurant>()

             restaurants.add(Restaurant("Якитория","Заказ от 300 Р", "Время доставки: 50 мин", R.drawable.food1))
             restaurants.add(Restaurant("Маки Маки","Заказ от 650 Р", "Время доставки: 60 мин", R.drawable.food2))
             restaurants.add(Restaurant("FoodBand","Заказ от 1000 Р", "Время доставки: 30 мин", R.drawable.food3))
             restaurants.add(Restaurant("Батони","Заказ от 450 Р", "Время доставки: 45 мин", R.drawable.food4))

             return restaurants
         }
     }
}