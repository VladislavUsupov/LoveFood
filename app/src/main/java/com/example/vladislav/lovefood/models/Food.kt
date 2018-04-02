package com.example.vladislav.lovefood.models

import com.example.vladislav.lovefood.R

data class Food (val nameFood: String, val description: String, val price: String, val imageUrl: String, val idRestaurant: String){

    class List : ArrayList<Food>()

    companion object {

        val food: ArrayList<Food> = generateFood()

        private fun generateFood(): ArrayList<Food> {

            var food = ArrayList<Food>()

            for (i in 0..9) {
                food.add(Food("Food", "that i love", "500P", "url", "id"))
            }

            return food
        }
    }
}
