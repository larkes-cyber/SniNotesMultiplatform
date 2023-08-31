package com.example.until

import android.content.Context
import android.content.SharedPreferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual fun createSettings(context: Context): Settings {
    val delegate: SharedPreferences = context.getSharedPreferences("AuthData", Context.MODE_PRIVATE)
    val settings: Settings = SharedPreferencesSettings()

    return Sett
}