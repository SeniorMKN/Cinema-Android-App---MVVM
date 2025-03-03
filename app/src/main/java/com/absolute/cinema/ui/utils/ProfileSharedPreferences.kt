package com.absolute.cinema.ui.utils

import android.content.Context
import android.content.SharedPreferences

object ProfileSharedPreferences {

    private const val PREF_NAME = "cinema_prefs"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setLoggedIn(context: Context, isLoggedIn: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun getIsLoggedIn(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun clearData(context: Context) {
        getPreferences(context).edit().clear().apply()
    }

}