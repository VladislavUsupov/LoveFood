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
import com.example.vladislav.lovefood.loadRestaurants
import com.example.vladislav.lovefood.loadRestaurantsFromDb
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch


class MainActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewMain)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        if ((application as App).isOnline()) {
            launch(UI) {
                val restaurants = async (CommonPool) {
                    loadRestaurants()
                }.await()
                recyclerView.adapter = RestaurantAdapter(restaurants) { restaurant ->
                    onRestaurantClick(restaurant)
                }
            }
        }
        else {
            recyclerView.adapter = RestaurantAdapter(loadRestaurantsFromDb()) { restaurant ->
                onRestaurantClick(restaurant)
            }
        }
    }



    private fun onRestaurantClick(restaurant: Restaurant){
        val intent = Intent(this, FoodActivity::class.java)
        intent.putExtra("id", restaurant.id)
        intent.putExtra("title", restaurant.nameRestaurant)
        startActivity(intent)
    }







}


