package com.example.vladislav.lovefood

import com.example.vladislav.lovefood.models.Food
import com.example.vladislav.lovefood.models.Restaurant
import com.google.gson.Gson
import io.paperdb.Paper
import okhttp3.*
import okhttp3.RequestBody
import okhttp3.OkHttpClient

const val URL_RESTAURANTS: String = "https://api.myjson.com/bins/1bxx8z"
const val URL_FOODS: String = "https://api.myjson.com/bins/uocpf"
const val URL_ORDERS: String = "https://api.myjson.com/bins/pwvv7"

fun loadRestaurants(): Restaurant.List {

    val restaurants = Gson().fromJson(getResponseText(URL_RESTAURANTS), Restaurant.List::class.java)
    Paper.book().write("restaurants", restaurants)

    return restaurants
}



fun loadFoodById(id: String): Food.List {

    val requestFoods: Food.List = Food.List()
    val foods = Gson().fromJson(getResponseText(URL_FOODS), Food.List::class.java)

    for (item in foods) {
        if (item.idRestaurant == id) {
            requestFoods.add(item)
        }
    }

    Paper.book("for-foods-"+id).write("foods",requestFoods)

    return requestFoods
}



fun loadOrderToServer(order: String){

    val json = MediaType.parse("application/json; charset=utf-8")
    val body = RequestBody.create(json, order)

    val client = OkHttpClient()

    val request = Request.Builder()
            .url(URL_ORDERS)
            .put(body)
            .build()

    client.newCall(request).execute()
}



fun loadRestaurantsFromDb(): Restaurant.List {
    try {
        return Paper.book().read("restaurants")
    }
    catch (e: Exception){
        return getDefaultRestaurant()
    }
}



fun loadFoodByIdFromDb(id: String): Food.List {
    try {
        return Paper.book("for-foods-" + id).read("foods")
    }
    catch (e: Exception){
        return getDefaultFood()
    }
}



private fun getResponseText(url: String): String {
    val httpClient = OkHttpClient()
    val request = Request.Builder()
            .url(url)
            .build()
    val response = httpClient.newCall(request).execute()

    return response.body()!!.string()
}



private fun getDefaultRestaurant(): Restaurant.List {

    val defaultList: Restaurant.List = Restaurant.List()
    val default = Restaurant(
            "1",
            "Name Restaurant",
            "Min Order Amount",
            "Delivery Time",
            "Url"
    )
    defaultList.add(default)

    return defaultList
}



private fun getDefaultFood(): Food.List {
    val defaultList: Food.List = Food.List()
    val default = Food(
            "1",
            "Name Food",
            "Price",
            "Url",
            "1"
    )
    defaultList.add(default)

    return defaultList
}



