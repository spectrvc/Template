package com.spector.template.data.storage

import android.content.Context
import android.content.SharedPreferences

private const val SHARED_PREFERENCES_NAME = "Settings"

class SharedPreferencesSettingsStorage(context: Context): SettingsStorage {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun saveMainSettings(settings: Settings) {
        sharedPreferences.edit().putString(settings.id, settings.value).apply()
    }

    override fun saveAccountSettings(settings: Settings, accountId: Int) {
        val commonId = "${settings.id}-$accountId"
        sharedPreferences.edit().putString(commonId, settings.value).apply()
    }

    override fun getMainSettings(id: String, defaultValue: String): Settings {
        val value = sharedPreferences.getString(id, defaultValue) ?: defaultValue
        return Settings(id = id, value = value)
    }

    override fun getAccountSettings(id: String, accountId: Int, defaultValue: String): Settings {
        val commonId = "$id-$accountId"
        val value = sharedPreferences.getString(commonId, defaultValue) ?: defaultValue
        return Settings(id = id, value = value)
    }

    override fun deleteAccountSettings(settingsId: String, accountId: Int) {
        val commonId = "$settingsId-$accountId"
        sharedPreferences.edit().remove(commonId).apply()
    }

}