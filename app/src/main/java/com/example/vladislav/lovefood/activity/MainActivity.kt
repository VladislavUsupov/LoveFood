package com.example.vladislav.lovefood.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.vladislav.lovefood.R
import com.example.vladislav.lovefood.adapters.RestaurantAdapter
import com.example.vladislav.lovefood.models.Restaurant

class MainActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView

    private val restaurantAdapter = RestaurantAdapter(Restaurant.restaurants) { restaurant ->
        onRestaurantClick(restaurant)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = restaurantAdapter

        restaurantAdapter.notifyDataSetChanged()
    }


    private fun onRestaurantClick(restaurant: Restaurant){
        Toast.makeText(this, "${restaurant.nameRestaurant}", Toast.LENGTH_SHORT). show()
        val intent = Intent(this, FoodActivity::class.java)
        startActivity(intent)
    }
}


