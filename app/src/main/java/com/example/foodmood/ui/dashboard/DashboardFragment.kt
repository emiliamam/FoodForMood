package com.example.foodmood.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.foodmood.databinding.FragmentDashboardBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmood.R
import com.example.foodmood.model.Restaurant
import RestaurantAdapter
import androidx.recyclerview.widget.GridLayoutManager

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        val favorites = FavoriteManager.getFavorites(requireContext())
        val recyclerView = binding.recyclerViewFavorites
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = RestaurantAdapter(favorites, requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
