package com.example.vladislav.lovefood.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.vladislav.lovefood.R
import com.example.vladislav.lovefood.models.Food
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_item.view.*

class FoodAdapter(
        private var items: ArrayList<Food>,
        private val onItemClick: (Food) -> Unit
): RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val food = items[position]
        holder?.txtNameFood?.text = food.nameFood
        holder?.txtPrice?.text = food.price
        try {
            Picasso.get().load(food.imageUrl).into(holder?.imgFood)
        }
        catch (e: Exception){
            holder?.imgFood?.setImageResource(R.drawable.imgdefault)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.food_item, parent, false)


        return ViewHolder(itemView).apply {
                    itemView.btnOrder.setOnClickListener {
                        val position = adapterPosition
                        val food = items[position]
                        onItemClick(food)
                    }
                }
    }



    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {

        var txtNameFood: TextView = row.findViewById(R.id.txtNameFood)
        var txtPrice: TextView = row.findViewById(R.id.txtPrice)
        var imgFood: ImageView = row.findViewById(R.id.imgFood)
    }
}
