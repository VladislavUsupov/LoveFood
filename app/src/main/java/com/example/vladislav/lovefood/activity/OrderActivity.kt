package com.example.vladislav.lovefood.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.vladislav.lovefood.R



class OrderActivity : AppCompatActivity(){

    private lateinit var buttonConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        buttonConfirm = findViewById(R.id.btnConfirm)
        buttonConfirm.setOnClickListener{
            Toast.makeText(this, "Заказ подтвержден", Toast.LENGTH_SHORT). show()
        }
    }
}
