package com.example.teachmenotes.data.shared_preferences

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(private val sharedPreferences: SharedPreferences){

    fun listLayoutPressed(){
        sharedPreferences.edit().putBoolean(LAYOUT_PRESSED, true).apply()
    }

    fun gridLayoutPressed(){
        sharedPreferences.edit().putBoolean(LAYOUT_PRESSED, false).apply()
    }

    fun checkLayout(): Boolean {
        return sharedPreferences.getBoolean(LAYOUT_PRESSED, false)
    }

    fun sortingByDateDESC(){
        sharedPreferences.edit().putBoolean(SORTING, true).apply()
    }

    fun sortingByDateASC(){
        sharedPreferences.edit().putBoolean(SORTING, false).apply()
    }

    fun checkSortType(): Boolean {
        return sharedPreferences.getBoolean(SORTING, true)
    }

    companion object{
        private const val LAYOUT_PRESSED = "LAYOUT_PRESSED"
        private const val SORTING = "SORTING"
    }

}