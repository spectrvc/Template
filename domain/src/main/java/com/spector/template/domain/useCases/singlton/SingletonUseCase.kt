package com.spector.template.domain.useCases.singlton

import com.spector.template.domain.convert.toTemplateBoolean
import com.spector.template.domain.convert.toTemplateString
import com.spector.template.domain.dto.models.Model
import com.spector.template.domain.dto.storage.MainSettingsDto
import com.spector.template.domain.enums.LanguageEnum
import com.spector.template.domain.enums.MainSettingsEnum
import com.spector.template.domain.repository.DeviceRepository
import com.spector.template.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow

class SingletonUseCase(
    private val settingsRepository: SettingsRepository,
    private val deviceRepository: DeviceRepository,
) {

    val model = MutableStateFlow(Model())

    fun init() {
        obtainModel()
    }

    private fun obtainModel() {
        val language = LanguageEnum.from(
            settingsRepository.getMainSettings(
                idEnum = MainSettingsEnum.LANGUAGE,
                defaultValue = LanguageEnum.UKRAINIAN.value
            )
        )

        //The locale must be set before getting setup and changing model.value  !!!
        deviceRepository.setLocale(language)

        model.value = Model(
            language = language,
            useDarkTheme = settingsRepository.getMainSettings(
                idEnum = MainSettingsEnum.DARK_THEME,
                defaultValue = false.toTemplateString()
            ).toTemplateBoolean(),
            password = settingsRepository.getMainSettings(
                idEnum = MainSettingsEnum.PASSWORD,
                defaultValue = ""
            ),
        )
    }

    fun saveMainSettings(mainSettingsDto: MainSettingsDto) {
        settingsRepository.saveMainSettings(mainSettingsDto)
        obtainModel()
    }

}