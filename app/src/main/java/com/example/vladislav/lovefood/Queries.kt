package com.example.vladislav.lovefood

import android.content.Context
import android.net.ConnectivityManager
import com.example.vladislav.lovefood.adapters.RestaurantAdapter
import com.example.vladislav.lovefood.models.Food
import com.example.vladislav.lovefood.models.Order
import com.example.vladislav.lovefood.models.Restaurant
import com.google.gson.Gson
import io.paperdb.Paper
import okhttp3.*
import java.io.IOException
import okhttp3.RequestBody
import java.lang.Integer.parseInt
import okhttp3.OkHttpClient

import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE




fun loadFoodById(id: String): Food.List {
    val httpClient = OkHttpClient()

    // Создать запрос
    val request = Request.Builder()
            .url("http://api.jsonbin.io/b/5ac26b892be5ef0bbf468526")
            .build()

    val response = httpClient.newCall(request).execute()

    // Обработать полученные данные
    val responseText = response.body()!!.string()

    System.out.println(responseText)

    val foods = Gson().fromJson(responseText, Food.List::class.java)

    Paper.book().write("foods", foods)

    val requestById: Food.List = Food.List()

    for (item in foods) {
        if (item.idRestaurant == id) {
            requestById.add(item)
        }
    }

    Paper.book("for-foods-"+id).write("foods",requestById)

    return requestById
}

fun loadOrderToServer(order: String){

    val JSON = MediaType.parse("application/json; charset=utf-8")
    val body = RequestBody.create(JSON, order)

    val client = OkHttpClient()

    val request = Request.Builder()
            .url("http://api.jsonbin.io/b/5ac33fc82be5ef0bbf4696da")
            .put(body) //PUT
            .build()

    client.newCall(request).execute()
}


fun getLastOrderId(): Int {
    val httpClient = OkHttpClient()

    // Создать запрос
    val request = Request.Builder()
            .url("http://api.jsonbin.io/b/5ac33fc82be5ef0bbf4696da")
            .build()

    val response = httpClient.newCall(request).execute()

    // Обработать полученные данные
    val responseText = response.body()!!.string()

    System.out.println(responseText)

    val orders = Gson().fromJson(responseText, Order.List::class.java)

//    if (orders.isEmpty()){
//        return 1
//    } else{
//        return orders[orders.size].id + 1
//    }
    return 1
}

fun loadFoodByIdFromCache(id: String): Food.List{

    val foods: Food.List = Paper.book("for-foods-"+id).read("foods")
    return foods

}


