package com.github.minimi.platformagnostic.storage

interface PlatformAgnosticStorage {
    fun getString(key: String, default: String? = null): String?
    fun putString(key: String, value: String)

    fun getInt(key: String, default: Int = 0): Int
    fun putInt(key: String, value: Int)

    fun getBoolean(key: String, default: Boolean = false): Boolean
    fun putBoolean(key: String, value: Boolean)

    fun remove(key: String)
}