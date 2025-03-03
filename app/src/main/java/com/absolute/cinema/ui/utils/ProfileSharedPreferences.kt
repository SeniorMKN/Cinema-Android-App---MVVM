package com.absolute.cinema.ui.utils

import android.content.Context
import android.content.SharedPreferences

object ProfileSharedPreferences {private const val PREF_NAME = "cinema_prefs"
    private const val KEY_PHONE_NUMBER = "phone_number"
    private const val KEY_PIN = "user_pin"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun savePhoneNumber(context: Context, phoneNumber: String) {
        getPreferences(context).edit().putString(KEY_PHONE_NUMBER, phoneNumber).apply()
    }

    fun getPhoneNumber(context: Context): String? {
        return getPreferences(context).getString(KEY_PHONE_NUMBER, null)
    }

    fun savePin(context: Context, pin: String) {
        getPreferences(context).edit().putString(KEY_PIN, pin).apply()
    }

    fun getPin(context: Context): String? {
        return getPreferences(context).getString(KEY_PIN, null)
    }

    fun clearData(context: Context) {
        getPreferences(context).edit().clear().apply()
    }

}