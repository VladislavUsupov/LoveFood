package com.example.vladislav.lovefood.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.vladislav.lovefood.*
import com.example.vladislav.lovefood.adapters.FoodAdapter
import com.example.vladislav.lovefood.models.Food
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch


class FoodActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val idRestaurant = intent.extras.getString("id")
        val title = intent.extras.getString("title")
        setTitle(title)

        recyclerView = findViewById(R.id.recyclerViewFood)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        if ((application as App).isOnline()) {
            launch(UI) {
                val foods = async (CommonPool) {
                    loadFoodById(idRestaurant)
                }.await()
                    recyclerView.adapter = FoodAdapter(foods) { food ->
                        onButtonOrderClick(food)
                    }
            }
        }
        else {
            recyclerView.adapter = FoodAdapter(loadFoodByIdFromDb(idRestaurant)) { food ->
                onButtonOrderClick(food)
            }
        }
    }



    private fun onButtonOrderClick(food: Food){
        val intent = Intent(this, OrderActivity::class.java)
        intent.putExtra("idFood",food.id)
        intent.putExtra("title", food.nameFood)
        startActivity(intent)
    }
}
