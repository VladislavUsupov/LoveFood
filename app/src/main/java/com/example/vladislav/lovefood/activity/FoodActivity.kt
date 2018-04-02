package com.example.vladislav.lovefood.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.vladislav.lovefood.R
import com.example.vladislav.lovefood.adapters.FoodAdapter
import com.example.vladislav.lovefood.loadFoodById
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

import org.jetbrains.anko.custom.async


class FoodActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var idRestaurant: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        idRestaurant = getIntent().getExtras().getString("id")

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        launch(UI) {
            async {

                recyclerView.adapter = FoodAdapter(loadFoodById(idRestaurant)) {
                    onButtonOrderClick()
                }
                recyclerView.adapter.notifyDataSetChanged()
            }
        }
    }


    private fun onButtonOrderClick(){
        val intent = Intent(this, OrderActivity::class.java)
        startActivity(intent)
    }
}
