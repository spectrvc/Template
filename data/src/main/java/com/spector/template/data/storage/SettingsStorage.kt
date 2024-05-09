package com.spector.template.data.storage

interface SettingsStorage {

    fun saveMainSettings(settings: Settings)

    fun saveAccountSettings(settings: Settings, accountId: Int)

    fun getMainSettings(id: String, defaultValue: String): Settings

    fun getAccountSettings(id: String, accountId: Int, defaultValue: String): Settings

    fun deleteAccountSettings(settingsId: String, accountId: Int)

}