package com.example.vladislav.lovefood.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.vladislav.lovefood.R
import com.example.vladislav.lovefood.adapters.FoodAdapter
import com.example.vladislav.lovefood.models.Food

class FoodActivity : AppCompatActivity(){

    private var recyclerView: RecyclerView? = null

    private val foodAdapter = FoodAdapter(Food.food) {
        foodName -> Toast.makeText(this, "${foodName.nameFood}", Toast.LENGTH_SHORT). show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_food)

        recyclerView = findViewById(R.id.recyclerView)
        val adapter = foodAdapter
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
