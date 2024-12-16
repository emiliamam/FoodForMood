package com.example.foodmood

import android.app.Dialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foodmood.R
import com.example.foodmood.databinding.ActivityMainRestaurantBinding
//import com.example.foodmood.databinding.ActivityMainRestaurantBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainRestaurantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainRestaurantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main_restaurant)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        // Слушатель для кнопки фильтра
//        binding.filterButton.setOnClickListener {
//            showFilterDialog()
//        }
    }

    private fun showFilterDialog() {
        // Создание и настройка диалога
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.filter_dialog)

        // Найти элементы интерфейса в диалоге
        val spinnerCuisine: Spinner = dialog.findViewById(R.id.filter_spinner)
        val editBudget: EditText = dialog.findViewById(R.id.filet_edit_budget)
        val btnSave: Button = dialog.findViewById(R.id.filter_save_button)

        // Настройка Spinner с адаптером
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.cuisine_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCuisine.adapter = adapter

        // Слушатель кнопки "Сохранить"
        btnSave.setOnClickListener {
            val selectedCuisine = spinnerCuisine.selectedItem.toString()
            val budget = editBudget.text.toString()

            // Обработка данных, например, передача их в Activity/Fragment
            Toast.makeText(this, "Кухня: $selectedCuisine, Бюджет: $budget", Toast.LENGTH_SHORT).show()

            dialog.dismiss()
        }

        // Показать диалог
        dialog.show()
    }
}
