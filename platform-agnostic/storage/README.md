
# Storage

Provides common interface for Key-Value storage for iOS and Android

For Android it implements `SharedPreferences` and for iOS implements `NSUserDefaults`.


```kotlin
// for Android
val prefs = context.getSharedPreferences(/* ... */)
val storage: PlatformAgnostincStorage = SharedPreferencesStorage(prefs)

// for iOS
val storage: PlatformAgnostincStorage = UserDefaultsStorage()

// Use it
storage.putString("myKey", "myValue")
val value = storage.getString("myKey")
```

Available methods:

```kotlin
interface PlatformAgnosticStorage {  
    fun getString(key: String, default: String? = null): String?  
    fun putString(key: String, value: String)  
  
    fun getInt(key: String, default: Int = 0): Int  
    fun putInt(key: String, value: Int)  
  
    fun getBoolean(key: String, default: Boolean = false): Boolean  
    fun putBoolean(key: String, value: Boolean)  
  
    fun remove(key: String)  
}
```

# Tasks to-do (Roadmap)

- [ ] Add methods for complex values such as List, Set and Map

# License
Apache 2.0