package com.absolute.cinema.ui.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.LocaleList
import java.util.Locale

object ProfileSharedPreferences {

    private const val PREF_NAME = "cinema_prefs"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"
    private const val KEY_LANGUAGE = "selected_language"

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

    fun saveLanguage(context: Context, locale: Locale) {
        getPreferences(context).edit().putString(KEY_LANGUAGE, locale.language).apply()
    }

    fun getSavedLanguage(context: Context): Locale {
        val languageCode =
            getPreferences(context).getString(KEY_LANGUAGE, Locale.getDefault().language)
        return Locale(languageCode ?: Locale.getDefault().language)
    }

    fun applySavedLanguage(context: Context): Context {
        val savedLocale = getSavedLanguage(context)
        return updateLanguage(context, savedLocale)
    }

    fun updateLanguage(context: Context, localeToSwitchTo: Locale): Context {
        saveLanguage(context, localeToSwitchTo)

        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        val localeList = LocaleList(localeToSwitchTo)

        LocaleList.setDefault(localeList)
        configuration.setLocales(localeList)

        return context.createConfigurationContext(configuration)
    }
}