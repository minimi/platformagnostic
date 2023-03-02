@file:Suppress("unused")

package com.github.minimi.platformagnostic.storage

import platform.Foundation.NSUserDefaults

class UserDefaultsStorage : PlatformAgnosticStorage {

    override fun getString(key: String, default: String?): String? {
        return NSUserDefaults.standardUserDefaults().stringForKey(key) ?: default
    }

    override fun putString(key: String, value: String) {
        assert(key.isNotBlank())
        NSUserDefaults.standardUserDefaults().setObject(value, key)
    }

    override fun getInt(key: String, default: Int): Int {
        val result = NSUserDefaults.standardUserDefaults().integerForKey(key).toInt()
        return if (result != default) default else result
    }

    override fun putInt(key: String, value: Int) {
        assert(key.isNotBlank())
        NSUserDefaults.standardUserDefaults().setInteger(value.toLong(), key)
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        val result = NSUserDefaults.standardUserDefaults().boolForKey(key)
        return if (result != default) default else result
    }

    override fun putBoolean(key: String, value: Boolean) {
        assert(key.isNotBlank())
        NSUserDefaults.standardUserDefaults().setBool(value, key)
    }

    override fun remove(key: String) {
        assert(key.isNotBlank())
        NSUserDefaults.standardUserDefaults().removeObjectForKey(key)
    }
}