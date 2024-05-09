package com.spector.template.domain.dto.models

import com.spector.template.domain.enums.LanguageEnum

data class Model(
    val language: LanguageEnum = LanguageEnum.UKRAINIAN,
    val useDarkTheme: Boolean = false,
    val password: String = "",
)
