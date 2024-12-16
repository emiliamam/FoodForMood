import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmood.R
import com.example.foodmood.model.Restaurant
import android.content.Intent
import android.content.Context
import com.example.foodmood.activity.RestaurantDetailActivity
import FavoriteManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class RestaurantAdapter(
    private val restaurantList: List<Restaurant>,
    private val context: Context
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.place_name_sample_two)
        val description: TextView = view.findViewById(R.id.place_description_sample_two)
        val favorite: ImageButton = view.findViewById(R.id.favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaraunt_card, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        holder.name.text = restaurant.name
        holder.description.text = "Средний чек: ${restaurant.average_check}"

        val favorites = FavoriteManager.getFavorites(context)
        val isFavorite = favorites.any { it.name == restaurant.name }

        updateFavoriteIcon(holder.favorite, isFavorite)

        holder.favorite.setOnClickListener {
            val currentFavorites = FavoriteManager.getFavorites(context).toMutableList()
            if (isFavorite) {
                // Удаление из избранного
                currentFavorites.removeAll { it.name == restaurant.name }
            } else {
                // Добавление в избранное
                currentFavorites.add(restaurant)
            }
            FavoriteManager.saveFavorites(context, currentFavorites)
            notifyItemChanged(position)
        }
    }

    private fun updateFavoriteIcon(favoriteButton: ImageButton, isFavorite: Boolean) {
        if (isFavorite) {
            favoriteButton.setBackgroundResource(R.drawable.baseline_favorite_24)
        } else {
            favoriteButton.setBackgroundResource(R.drawable.baseline_favorite_border_24)
        }
    }

    override fun getItemCount() = restaurantList.size
}

object FavoriteManager {

    private const val PREF_NAME = "favorites_pref"
    private const val FAVORITES_KEY = "favorites_key"

    fun saveFavorites(context: Context, favorites: List<Restaurant>) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(favorites)
        editor.putString(FAVORITES_KEY, json)
        editor.apply()
    }

    fun getFavorites(context: Context): List<Restaurant> {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(FAVORITES_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<Restaurant>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }
}