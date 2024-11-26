package com.example.foodmood.activity

import android.app.Dialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.foodmood.R

class MainPageActivity : AppCompatActivity() {

    private lateinit var searchButton: EditText
    private lateinit var filterButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restraunt_cards)

        // Инициализация основных элементов
        searchButton = findViewById(R.id.search_button)
        filterButton = findViewById(R.id.filter_button)

        // Слушатель для кнопки фильтра
        filterButton.setOnClickListener {
            showFilterDialog()
        }
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
            R.array.cuisine_array, // ID массива строк
            android.R.layout.simple_spinner_item // Макет для каждого элемента списка
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCuisine.adapter = adapter

        // Слушатель кнопки "Сохранить"
        btnSave.setOnClickListener {
            val selectedCuisine = spinnerCuisine.selectedItem.toString() // Получить выбранный элемент
            val budget = editBudget.text.toString() // Получить введенный бюджет

            // Здесь можно обработать данные, например, передать их в Activity/Fragment
            Toast.makeText(this, "Кухня: $selectedCuisine, Бюджет: $budget", Toast.LENGTH_SHORT).show()

            dialog.dismiss() // Закрыть диалог
        }

        // Показать диалог
        dialog.show()
    }
}
