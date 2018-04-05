package com.example.vladislav.lovefood.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import java.util.Calendar
import com.example.vladislav.lovefood.models.Order
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import android.widget.Toast
import com.example.vladislav.lovefood.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async


class OrderActivity : AppCompatActivity(){

    private lateinit var nameClient: EditText
    private lateinit var address: EditText
    private lateinit var phone: EditText
    private lateinit var date: String
    private lateinit var buttonConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val idFood = intent.extras.getString("idFood")
        val title = intent.extras.getString("title")
        setTitle(title)

        nameClient = findViewById(R.id.editName)
        address = findViewById(R.id.editAddress)
        phone = findViewById(R.id.editPhone)
        date = Calendar.getInstance().time.toString()
        buttonConfirm = findViewById(R.id.btnConfirm)

        buttonConfirm.setOnClickListener {

            if ((application as App).isOnline()) {
                launch(UI) {
                    async(CommonPool) {
                        val requestOrder = Order(
                                "1",
                                nameClient.text.toString(),
                                address.text.toString(),
                                phone.text.toString(),
                                date,
                                idFood
                        )

                        loadOrderToServer(Gson().toJson(requestOrder))
                    }
                }
                Toast.makeText(this, "Заказ подтвержден", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Нет подключения к интернету", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
