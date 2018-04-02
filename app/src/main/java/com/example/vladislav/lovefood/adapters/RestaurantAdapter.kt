package com.example.vladislav.lovefood.adapters


import android.media.Image
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
        Picasso.get().load(restaurant.imgRestaurant).into(holder?.imgRestaurant)
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

        var txtNameRestaurant: TextView? = null
        var txtMinOrderAmount: TextView? = null
        var txtDeliveryTime: TextView? = null
        var imgRestaurant: ImageView? = null


        init {
            this.txtNameRestaurant = row.findViewById<TextView>(R.id.txtNameRestaurant)
            this.txtMinOrderAmount = row.findViewById<TextView>(R.id.txtMinOrderAmount)
            this.txtDeliveryTime = row.findViewById<TextView>(R.id.txtDeliveryTime)
            this.imgRestaurant = row.findViewById<ImageView>(R.id.imgRestaurant)
        }
    }
}