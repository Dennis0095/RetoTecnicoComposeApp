package com.pe.manager.infrastructure.di

import android.content.SharedPreferences

class LocalStorage(private val prefs: SharedPreferences) {
    companion object {

    }

    fun putString(key: String, value: String?) {
        prefs.edit().apply { putString(key, value) }.apply()
    }

    fun getString(key: String): String? {
        return prefs.getString(key, null)
    }
}