package com.spector.template.data.repository

import com.spector.template.data.storage.Settings
import com.spector.template.data.storage.SettingsStorage
import com.spector.template.domain.dto.storage.MainSettingsDto
import com.spector.template.domain.enums.MainSettingsEnum
import com.spector.template.domain.repository.SettingsRepository

class SettingsRepositoryImpl(private val settingsStorage: SettingsStorage) : SettingsRepository {

    override fun saveMainSettings(mainSettingsDto: MainSettingsDto) {
        settingsStorage.saveMainSettings(mainSettingsDto.toSettings())
    }

    override fun getMainSettings(idEnum: MainSettingsEnum, defaultValue: String): String {
        return settingsStorage.getMainSettings(
            id = idEnum.name,
            defaultValue = defaultValue
        ).value
    }

    private fun MainSettingsDto.toSettings() = Settings(
        id = enum.toString(),
        value = value
    )
}