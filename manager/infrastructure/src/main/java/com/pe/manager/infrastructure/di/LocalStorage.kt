package com.pe.manager.infrastructure.di

import android.content.SharedPreferences

class LocalStorage(private val prefs: SharedPreferences) {
    companion object {
        const val KEY_LIST_APPS = "registered_applications"
    }

    fun putString(key: String, value: String?) {
        prefs.edit().apply { putString(key, value) }.apply()
    }

    fun getString(key: String): String? {
        return prefs.getString(key, null)
    }
}