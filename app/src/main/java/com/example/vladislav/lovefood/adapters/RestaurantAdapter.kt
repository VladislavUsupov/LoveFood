package com.example.vladislav.lovefood.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.vladislav.lovefood.R
import com.example.vladislav.lovefood.models.Restaurant
import android.widget.ImageView
import com.squareup.picasso.Picasso

class RestaurantAdapter(
        private var items: ArrayList<Restaurant>,
        private val onItemClick: (Restaurant) -> Unit
): RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        var restaurant = items[position]
        holder?.txtNameRestaurant?.text = restaurant.nameRestaurant
        holder?.txtMinOrderAmount?.text = restaurant.minOrderAmount
        holder?.txtDeliveryTime?.text = restaurant.deliveryTime
        try {
            Picasso.get().load(restaurant.imageUrl).into(holder?.imgRestaurant)
        }
        catch (e: Exception) {
            holder?.imgRestaurant?.setImageResource(R.drawable.imgdefault)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.restaurant_item, parent, false)

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

        var txtNameRestaurant: TextView = row.findViewById(R.id.txtNameRestaurant)
        var txtMinOrderAmount: TextView = row.findViewById(R.id.txtMinOrderAmount)
        var txtDeliveryTime: TextView = row.findViewById(R.id.txtDeliveryTime)
        var imgRestaurant: ImageView = row.findViewById(R.id.imgRestaurant)
    }
}