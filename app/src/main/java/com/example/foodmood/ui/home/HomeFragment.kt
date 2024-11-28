package com.example.foodmood.ui.home

import RestaurantAdapter
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmood.R
import com.example.foodmood.model.Restaurant
import com.example.foodmood.databinding.FragmentHomeBinding
import com.example.foodmood.networkRetrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
//        val filterButton: Button = binding.filter_button

        // Слушатель для кнопки фильтра
//        filterButton.setOnClickListener {
//            showFilterDialog()
//        }
        return root
    }

    private fun setupRecyclerView() {

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.api.getRestaurants(page = 1, pageSize = 20)
                val restaurantList = response.data
                val adapter = RestaurantAdapter(restaurantList)
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                e.printStackTrace()
                // Обработка ошибок
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//    private fun showFilterDialog() {
//        // Создание и настройка диалога
//        val dialog = Dialog(this)
//        dialog.setContentView(R.layout.filter_dialog)
//
//        // Найти элементы интерфейса в диалоге
//        val spinnerCuisine: Spinner = dialog.findViewById(R.id.filter_spinner)
//        val editBudget: EditText = dialog.findViewById(R.id.filet_edit_budget)
//        val btnSave: Button = dialog.findViewById(R.id.filter_save_button)
//
//        // Настройка Spinner с адаптером
//        val adapter = ArrayAdapter.createFromResource(
//            this,
//            R.array.cuisine_array, // ID массива строк
//            android.R.layout.simple_spinner_item // Макет для каждого элемента списка
//        )
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinnerCuisine.adapter = adapter
//
//        // Слушатель кнопки "Сохранить"
//        btnSave.setOnClickListener {
//            val selectedCuisine = spinnerCuisine.selectedItem.toString() // Получить выбранный элемент
//            val budget = editBudget.text.toString() // Получить введенный бюджет
//
//            // Здесь можно обработать данные, например, передать их в Activity/Fragment
//            Toast.makeText(this, "Кухня: $selectedCuisine, Бюджет: $budget", Toast.LENGTH_SHORT).show()
//
//            dialog.dismiss() // Закрыть диалог
//        }
//
//        // Показать диалог
//        dialog.show()
//    }
}
