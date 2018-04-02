package com.example.vladislav.lovefood.models

import android.widget.ImageView
import com.example.vladislav.lovefood.R

data class Restaurant (val nameRestaurant: String, val minOrderAmount: String, val deliveryTime: String, val imgRestaurant: String){

    class List : ArrayList<Restaurant>()

     companion object {

         val restaurants: ArrayList<Restaurant> = generateRestaurant()

         private fun generateRestaurant(): ArrayList<Restaurant> {

             var restaurants = ArrayList<Restaurant>()

             restaurants.add(Restaurant("Якитория","Заказ от 300 Р", "Время доставки: 50 мин", "url"))
             restaurants.add(Restaurant("Маки Маки","Заказ от 650 Р", "Время доставки: 60 мин", "url"))
             restaurants.add(Restaurant("FoodBand","Заказ от 1000 Р", "Время доставки: 30 мин", "url"))
             restaurants.add(Restaurant("Батони","Заказ от 450 Р", "Время доставки: 45 мин", "url"))

             return restaurants
         }
     }
}