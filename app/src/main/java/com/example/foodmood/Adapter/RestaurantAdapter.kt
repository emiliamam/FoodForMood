package com.example.foodmood.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmood.R
import com.example.foodmood.model.Restaurant

class RestaurantAdapter(
    private val restaurantList: List<Restaurant>
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    inner class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.place_name_sample_one)
        val descriptionTextView: TextView = view.findViewById(R.id.place_description_sample_one)
//        val estimationTextView: TextView = view.findViewById(R.id.place_estimation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restraunt_cards, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        holder.nameTextView.text = restaurant.name
        holder.descriptionTextView.text = "Кухня: ${restaurant.kitchen}"
//        holder.estimationTextView.text = "Оценка: ${restaurant.estimation}"
    }

    override fun getItemCount() = restaurantList.size
}
