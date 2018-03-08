package com.example.vladislav.lovefood.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.vladislav.lovefood.R
import com.example.vladislav.lovefood.adapters.FoodAdapter
import com.example.vladislav.lovefood.models.Food

class FoodActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView

    private val foodAdapter = FoodAdapter(Food.food) {  foodName ->
        Toast.makeText(this, "${foodName.nameFood}", Toast.LENGTH_SHORT). show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = foodAdapter

        foodAdapter.notifyDataSetChanged()
    }
}
