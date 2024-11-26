import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmood.R
import com.example.foodmood.model.Restaurant

class RestaurantAdapter(private val restaurantList: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.place_name_sample_two)
        val description: TextView = view.findViewById(R.id.place_description_sample_two)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaraunt_card, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        holder.name.text = restaurant.name
        holder.description.text = "Кухня: ${restaurant.kitchen}, Средний чек: ${restaurant.average_check}"
    }

    override fun getItemCount() = restaurantList.size
}