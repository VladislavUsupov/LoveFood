package com.example.vladislav.lovefood.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.vladislav.lovefood.R
import com.example.vladislav.lovefood.models.Food

class FoodAdapter(
        private var items: ArrayList<Food>,
        private val onItemClick: (Food) -> Unit
): RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var food = items[position]
        holder?.txtNameFood?.text = food.nameFood
        holder?.txtDescription?.text = food.description
        holder?.txtPrice?.text = food.price
        holder?.imgFood?.setImageResource(food.imageFood)
    }



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.food_item, parent, false)

        return ViewHolder(itemView).apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val name = items[position]
                    onItemClick(name)
                }
            }
        }
    }



    class ViewHolder(row: View) : RecyclerView.ViewHolder(row){

        var txtNameFood: TextView? = null
        var txtDescription: TextView? = null
        var txtPrice: TextView? = null
        var imgFood: ImageView? = null

        init {
            this.txtNameFood = row.findViewById<TextView>(R.id.txtNameFood)
            this.txtDescription = row.findViewById<TextView>(R.id.txtDescription)
            this.txtPrice = row.findViewById<TextView>(R.id.txtPrice)
            this.imgFood = row.findViewById<ImageView>(R.id.imgFood)
        }
    }
}