package com.example.vladislav.lovefood

import com.example.vladislav.lovefood.models.Food
import com.google.gson.Gson
import io.paperdb.Paper
import okhttp3.*
import okhttp3.RequestBody
import okhttp3.OkHttpClient

fun loadFoodById(id: String): Food.List {
    val httpClient = OkHttpClient()
    val request = Request.Builder()
            .url("http://api.jsonbin.io/b/5ac26b892be5ef0bbf468526")
            .build()

    val response = httpClient.newCall(request).execute()

    val responseText = response.body()!!.string()
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
//    val httpClient = OkHttpClient()
//
//    val request = Request.Builder()
//            .url("http://api.jsonbin.io/b/5ac33fc82be5ef0bbf4696da")
//            .build()
//
//    val response = httpClient.newCall(request).execute()
//
//    val responseText = response.body()!!.string()
//
//    val orders = Gson().fromJson(responseText, Order.List::class.java)

    return 1
}

fun loadFoodByIdFromDb(id: String): Food.List{

    return Paper.book("for-foods-"+id).read("foods")
}


