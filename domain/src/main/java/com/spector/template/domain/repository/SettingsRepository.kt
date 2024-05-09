package com.spector.template.domain.repository

import com.spector.template.domain.dto.storage.MainSettingsDto
import com.spector.template.domain.enums.MainSettingsEnum

interface SettingsRepository {

    fun saveMainSettings(mainSettingsDto: MainSettingsDto)

    fun getMainSettings(idEnum: MainSettingsEnum, defaultValue: String): String

}