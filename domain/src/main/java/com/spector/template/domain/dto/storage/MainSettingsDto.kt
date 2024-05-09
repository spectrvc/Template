package com.spector.template.domain.dto.storage

import com.spector.template.domain.enums.MainSettingsEnum

data class MainSettingsDto(
    val enum: MainSettingsEnum,
    val value: String
)
