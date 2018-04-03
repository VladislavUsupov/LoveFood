package com.example.vladislav.lovefood.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import java.util.Calendar
import com.example.vladislav.lovefood.R
import com.example.vladislav.lovefood.adapters.FoodAdapter
import com.example.vladislav.lovefood.getLastOrderId
import com.example.vladislav.lovefood.loadFoodById
import com.example.vladislav.lovefood.loadOrderToServer
import com.example.vladislav.lovefood.models.Order
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.custom.async
import android.widget.Toast



class OrderActivity : AppCompatActivity(){

    private lateinit var buttonConfirm: Button

    private lateinit var idFood: String
    private lateinit var nameFood: String

    private lateinit var nameClient: EditText
    private lateinit var address: EditText
    private lateinit var phone: EditText
    private lateinit var date: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        idFood = getIntent().getExtras().getString("idFood")
        nameFood = getIntent().getExtras().getString("nameFood")

        nameClient = findViewById(R.id.editName)
        address = findViewById(R.id.editAddress)
        phone = findViewById(R.id.editPhone)
        date = Calendar.getInstance().getTime().toString()
        buttonConfirm = findViewById(R.id.btnConfirm)

        buttonConfirm.setOnClickListener {

            launch(UI) {
                async {
                    val requestOrder = Order(
                            getLastOrderId(),
                            nameClient.getText().toString(),
                            address.getText().toString(),
                            phone.getText().toString(),
                            date,
                            idFood
                    )

                    loadOrderToServer(Gson().toJson(requestOrder))

                }
            }
            Toast.makeText(this, "Заказ подтвержден", Toast.LENGTH_SHORT). show()
        }
    }
}
