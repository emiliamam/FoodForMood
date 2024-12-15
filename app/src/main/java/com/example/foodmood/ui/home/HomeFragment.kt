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
import androidx.recyclerview.widget.GridLayoutManager
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

    private var selectedCuisine: String = "Все кухни" // Заголовок по умолчанию

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Изначальный заголовок
        binding.italianKitchenTopic.text = selectedCuisine

        setupRecyclerView()

        // Привязка к кнопке фильтра
        binding.filterButton.setOnClickListener {
            showFilterDialog()
        }

        binding.functionalButton.setOnClickListener {
            showFunctionalInfo()
        }

        binding.questionsButton.setOnClickListener {
            showFAQ()
        }

        binding.otherButton.setOnClickListener {
            showOtherInfo()
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2) // Сетка из двух колонок

        lifecycleScope.launch {
            try {
                // Изначально выводим все рестораны
                val response = RetrofitClient.api.filterRestaurants(page = 1, pageSize = 20)
                updateRecyclerView(response.data)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Ошибка загрузки ресторанов", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showFunctionalInfo() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_functional)

        dialog.setCancelable(true)
        dialog.show()
    }

    private fun showFAQ() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_faq)

        dialog.setCancelable(true)
        dialog.show()
    }

    private fun showOtherInfo() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_other)

        dialog.setCancelable(true)
        dialog.show()
    }

    private fun showFilterDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.filter_dialog)

        val spinnerCuisine: Spinner = dialog.findViewById(R.id.filter_spinner)
        val editBudget: EditText = dialog.findViewById(R.id.filet_edit_budget)
        val btnSave: Button = dialog.findViewById(R.id.filter_save_button)

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.cuisine_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCuisine.adapter = adapter

        btnSave.setOnClickListener {
            val selectedCuisineText = spinnerCuisine.selectedItem.toString()
            val budget = editBudget.text.toString()

            lifecycleScope.launch {
                try {
                    // Запрос к бэкенду для фильтрации
                    val response = RetrofitClient.api.filterRestaurants(
                        page = 1,
                        pageSize = 20,
                        averageCheck = if (budget.isNotEmpty()) budget else null,
                        kitchen = if (selectedCuisineText.isNotEmpty() && selectedCuisineText != "Все кухни") selectedCuisineText else null
                    )

                    // Обновление заголовка и RecyclerView
                    selectedCuisine = if (selectedCuisineText.isNotEmpty()) selectedCuisineText else "Все кухни"
                    binding.italianKitchenTopic.text = selectedCuisine

                    updateRecyclerView(response.data)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Ошибка фильтрации", Toast.LENGTH_SHORT).show()
                }
            }

            dialog.dismiss()
        }

        dialog.show()
    }

    private fun updateRecyclerView(filteredRestaurants: List<Restaurant>) {
        val adapter = RestaurantAdapter(filteredRestaurants)
        binding.recyclerView.adapter = adapter
    }

}
