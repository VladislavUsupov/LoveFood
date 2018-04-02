package com.example.vladislav.lovefood.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.vladislav.lovefood.R
import com.example.vladislav.lovefood.models.Food
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_item.view.*

class FoodAdapter(
        private var items: ArrayList<Food>,
        private val onItemClick: () -> Unit
): RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var food = items[position]
        holder?.txtNameFood?.text = food.nameFood
        holder?.txtDescription?.text = food.description
        holder?.txtPrice?.text = food.price
        Picasso.get().load(food.imageUrl).into(holder?.imgFood)
//        holder?.imgFood?.setImageResource(food.imageFood)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.food_item, parent, false)


        return ViewHolder(itemView).apply {
                    itemView.btnOrder.setOnClickListener {
                        onItemClick()
                    }
                }
    }


    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {

        var txtNameFood: TextView? = null
        var txtDescription: TextView? = null
        var txtPrice: TextView? = null
        var imgFood: ImageView? = null
        var btnOrder: Button? = null

        init {
            this.txtNameFood = row.findViewById<TextView>(R.id.txtNameFood)
            this.txtDescription = row.findViewById<TextView>(R.id.txtDescription)
            this.txtPrice = row.findViewById<TextView>(R.id.txtPrice)
            this.imgFood = row.findViewById<ImageView>(R.id.imgFood)
            this.btnOrder = row.findViewById<Button>(R.id.btnOrder)
        }
    }
}
