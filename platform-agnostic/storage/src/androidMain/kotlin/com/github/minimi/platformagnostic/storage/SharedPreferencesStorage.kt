@file:Suppress("unused")

package com.github.minimi.platformagnostic.storage

import android.content.SharedPreferences

class SharedPreferencesStorage(private val prefs: SharedPreferences): PlatformAgnosticStorage {

    override fun getString(key: String, default: String?): String? {
        return prefs.getString(key, default)
    }

    override fun putString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    override fun getInt(key: String, default: Int): Int {
        return prefs.getInt(key, default)
    }

    override fun putInt(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        return prefs.getBoolean(key, default)
    }

    override fun putBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    override fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }
}