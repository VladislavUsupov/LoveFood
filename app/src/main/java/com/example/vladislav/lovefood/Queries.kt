package com.example.vladislav.lovefood

import com.example.vladislav.lovefood.adapters.RestaurantAdapter
import com.example.vladislav.lovefood.models.Food
import com.example.vladislav.lovefood.models.Restaurant
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

fun loadFoodById(id: String): Food.List {
    val httpClient = OkHttpClient()

    // Создать запрос
    val request = Request.Builder()
            .url("http://api.jsonbin.io/b/5ac11fd12be5ef0bbf466903")
            .build()

    val response = httpClient.newCall(request).execute()

    // Обработать полученные данные
    val responseText = response.body()!!.string()
    val foods = Gson().fromJson(responseText, Food.List::class.java)

    val requestById: Food.List = Food.List()

    for (item in foods) {
        if (item.idRestaurant == id) {
            requestById.add(item)
        }
    }

    return requestById
}