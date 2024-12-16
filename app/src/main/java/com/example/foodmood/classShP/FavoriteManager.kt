package com.example.foodmood.classShP

import android.content.Context
import com.example.foodmood.model.Restaurant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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
