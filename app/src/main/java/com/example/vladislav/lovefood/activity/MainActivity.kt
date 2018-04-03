package com.example.vladislav.lovefood.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.vladislav.lovefood.R
import com.example.vladislav.lovefood.adapters.RestaurantAdapter
import com.example.vladislav.lovefood.models.Restaurant
import com.google.gson.Gson
import io.paperdb.Paper
import okhttp3.*
import java.io.IOException
import com.example.vladislav.lovefood.App


class MainActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        loadRestaurants()
    }



    private fun onRestaurantClick(restaurant: Restaurant){
        val intent = Intent(this, FoodActivity::class.java)
        intent.putExtra("id", "${restaurant.id}")
        startActivity(intent)
    }



    private fun loadRestaurants(){

        if ((application as App).isOnline()){
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url("https://api.jsonbin.io/b/5ac0c95b2be5ef0bbf466182/7")
                    .build()
            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call?, response: Response?) {
                    val responseText = response?.body()!!.string()
                    val restaurants = Gson().fromJson(responseText, Restaurant.List::class.java)

                    Paper.book().write("restaurants", restaurants)

                    runOnUiThread {
                        recyclerView.adapter = RestaurantAdapter(restaurants) { restaurant ->
                            onRestaurantClick(restaurant)
                        }
                    }
                }

                override fun onFailure(call: Call?, e: IOException?) {
                    println("Failed to execute request")
                }
            })
        }
        else {
            val restaurants: Restaurant.List = Paper.book().read("restaurants")
            recyclerView.adapter = RestaurantAdapter(restaurants) {restaurant ->
                onRestaurantClick(restaurant)
            }
        }
        recyclerView.adapter.notifyDataSetChanged()
    }



}


