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


class RestaurantAdapter(private val restaurantList: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

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
        val context = holder.itemView.context

        holder.name.text = restaurant.name
        holder.description.text = "Средний чек: ${restaurant.average_check}"

        val isFavorite = restaurant.isFavorite
        updateFavoriteIcon(holder.favorite, isFavorite)

        holder.favorite.setOnClickListener {
            restaurant.isFavorite = !restaurant.isFavorite

            updateFavoriteIcon(holder.favorite, restaurant.isFavorite)

//            updateFavoriteInBackend(restaurant.id, restaurant.isFavorite)

            notifyItemChanged(position)
        }
        holder.itemView.setOnClickListener {
            // Переход на новый экран с подробной информацией
            val intent = Intent(context, RestaurantDetailActivity::class.java)
            // Передаем данные о ресторане через Intent
            intent.putExtra("RESTAURANT_NAME", restaurant.name)
            intent.putExtra("RESTAURANT_DESCRIPTION", restaurant.description)
            intent.putExtra("RESTAURANT_KITCHEN", restaurant.kitchen)
            intent.putExtra("RESTAURANT_ESTIMATION", restaurant.estimation)
            intent.putExtra("RESTAURANT_TYPE", restaurant.type)
            context.startActivity(intent)
        }
    }

    private fun updateFavoriteIcon(favoriteButton: ImageButton, isFavorite: Boolean) {
        if (isFavorite) {
            favoriteButton.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            favoriteButton.setImageResource(R.drawable.baseline_favorite_border_24)
        }
    }

    override fun getItemCount() = restaurantList.size
}
