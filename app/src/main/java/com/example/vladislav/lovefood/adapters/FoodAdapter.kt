package com.example.vladislav.lovefood.adapters

import android.accounts.NetworkErrorException
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
        private val onItemClick: (Food) -> Unit
): RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var food = items[position]
        holder?.txtNameFood?.text = food.nameFood
        holder?.txtDescription?.text = food.description
        holder?.txtPrice?.text = food.price
        try {
            Picasso.get().load(food.imageUrl).into(holder?.imgFood)
        }
        catch (e: NetworkErrorException){
            holder?.imgFood?.setImageResource(R.drawable.food2)
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

        var txtNameFood: TextView? = null
        var txtDescription: TextView? = null
        var txtPrice: TextView? = null
        var imgFood: ImageView? = null
        var btnOrder: Button? = null

        init {
            this.txtNameFood = row.findViewById(R.id.txtNameFood)
            this.txtDescription = row.findViewById(R.id.txtDescription)
            this.txtPrice = row.findViewById(R.id.txtPrice)
            this.imgFood = row.findViewById(R.id.imgFood)
            this.btnOrder = row.findViewById(R.id.btnOrder)
        }
    }
}
