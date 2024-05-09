package com.spector.template.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spector.template.R
import com.spector.template.domain.convert.toTemplateString
import com.spector.template.domain.dto.common.ItemDto
import com.spector.template.domain.dto.storage.MainSettingsDto
import com.spector.template.domain.enums.FixedListEnum
import com.spector.template.domain.enums.FixedListTypeEnum
import com.spector.template.domain.enums.LanguageEnum
import com.spector.template.domain.enums.MainSettingsEnum
import com.spector.template.domain.useCases.ResourceUseCase
import com.spector.template.domain.useCases.singlton.SingletonUseCase
import com.spector.template.presentation.customElements.dialogs.EditDialogDto
import com.spector.template.presentation.customElements.dialogs.ListDialogDto
import com.spector.template.presentation.customElements.lists.FixedListItemDto
import com.spector.template.presentation.enums.EditDialogEnum
import com.spector.template.presentation.enums.ListDialogEnum
import com.spector.template.presentation.main.description.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val singletonUseCase: SingletonUseCase,
    private val resourceUseCase: ResourceUseCase,
) : ViewModel(), Store<SettingsState, SettingsSideEffect> {

    private val state = MutableStateFlow(SettingsState())

    private val sideEffect = MutableSharedFlow<SettingsSideEffect>()

    override fun observeState(): StateFlow<SettingsState> = state

    override fun observeSideEffect(): SharedFlow<SettingsSideEffect> = sideEffect

    init {
        viewModelScope.launch(Dispatchers.IO) {
            singletonUseCase.model.collect {
                state.value = state.value.copy(model = it)
                obtainList()
            }
        }
    }

    private fun obtainList() {
        val languageText = when (state.value.model.language) {
            LanguageEnum.ENGLISH -> resourceUseCase.getResourceString(R.string.settingsLanguageEnglish)
            LanguageEnum.RUSSIAN -> resourceUseCase.getResourceString(R.string.settingsLanguageRussian)
            LanguageEnum.UKRAINIAN -> resourceUseCase.getResourceString(R.string.settingsLanguageUkrainian)
            else -> resourceUseCase.getResourceString(R.string.settingsLanguageUkrainian)
        }
        val list = mutableListOf(
            FixedListItemDto(
                id = FixedListEnum.SETTINGS_LANGUAGE,
                title = resourceUseCase.getResourceString(R.string.settingsLabelLanguage),
                text = languageText
            ),
            FixedListItemDto(
                id = FixedListEnum.SETTINGS_DARK_THEME,
                title = resourceUseCase.getResourceString(R.string.settingsLabelDarkTheme),
                type = FixedListTypeEnum.BOOLEAN,
                bool = state.value.model.useDarkTheme
            ),
            FixedListItemDto(
                id = FixedListEnum.SETTINGS_PASSWORD,
                title = resourceUseCase.getResourceString(R.string.settingsLabelPassword),
                text = state.value.model.password
            ),
        )
        state.value = state.value.copy(list = list)
    }

    fun onSelectListItem(listItemDto: FixedListItemDto) {
        when (listItemDto.id) {
            FixedListEnum.SETTINGS_LANGUAGE -> onClickLanguage()
            FixedListEnum.SETTINGS_DARK_THEME -> saveDarkTheme(!listItemDto.bool)
            FixedListEnum.SETTINGS_PASSWORD -> onClickPassword()
            else -> {}
        }
    }

    private fun saveDarkTheme(value: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            singletonUseCase.saveMainSettings(
                mainSettingsDto = MainSettingsDto(
                    enum = MainSettingsEnum.DARK_THEME,
                    value = value.toTemplateString()
                )
            )
        }
    }

    private fun onClickLanguage() {
        val listDialogDto = ListDialogDto(
            id = ListDialogEnum.LANGUAGE,
            show = true,
            title = resourceUseCase.getResourceString(R.string.settingsLabelLanguage),
            list = getLanguageList()
        )
        showListDialog(listDialogDto)
    }

    private fun getLanguageList(): List<ItemDto> {
        return listOf(
            ItemDto(
                LanguageEnum.ENGLISH.value,
                resourceUseCase.getResourceString(R.string.settingsLanguageEnglish)
            ),
            ItemDto(
                LanguageEnum.RUSSIAN.value,
                resourceUseCase.getResourceString(R.string.settingsLanguageRussian)
            ),
            ItemDto(
                LanguageEnum.UKRAINIAN.value,
                resourceUseCase.getResourceString(R.string.settingsLanguageUkrainian)
            )
        )
    }

    private fun onClickPassword() {
        val customEditDialogDto = EditDialogDto(
            id = EditDialogEnum.SETTINGS_PASSWORD,
            show = true,
            title = resourceUseCase.getResourceString(R.string.settingsLabelPassword),
            text = state.value.model.password
        )
        state.value = state.value.copy(editDialogDto = customEditDialogDto)
    }

    private fun showListDialog(listDialogDto: ListDialogDto) {
        state.value = state.value.copy(listDialogDto = listDialogDto)
    }

    fun hideListDialog() {
        state.value = state.value.copy(listDialogDto = ListDialogDto())
    }

    fun hideEditDialog() {
        state.value = state.value.copy(editDialogDto = EditDialogDto())
    }

    fun onSelectListDialogItem(dialogId: ListDialogEnum, itemId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (dialogId) {
                ListDialogEnum.LANGUAGE -> singletonUseCase.saveMainSettings(
                    mainSettingsDto = MainSettingsDto(
                        enum = MainSettingsEnum.LANGUAGE,
                        value = itemId
                    )
                )

                else -> {}
            }
            state.value = state.value.copy(listDialogDto = ListDialogDto())
        }
    }

    fun onClickEditDialogOk(dialogId: EditDialogEnum, value: String) {
        if (dialogId == EditDialogEnum.SETTINGS_PASSWORD) {
            viewModelScope.launch(Dispatchers.IO) {
                singletonUseCase.saveMainSettings(
                    mainSettingsDto = MainSettingsDto(
                        enum = MainSettingsEnum.PASSWORD,
                        value = value
                    )
                )
                hideEditDialog()
            }
        }
    }

}