package com.spector.template.domain.repository

import com.spector.template.domain.enums.LanguageEnum

interface DeviceRepository {

    fun setLocale(languageEnum: LanguageEnum)

    fun getResourceString(resourceId: Int): String

}