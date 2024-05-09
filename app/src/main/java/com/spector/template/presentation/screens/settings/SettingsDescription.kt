package com.spector.template.presentation.screens.settings

import com.spector.template.domain.dto.models.Model
import com.spector.template.presentation.customElements.dialogs.EditDialogDto
import com.spector.template.presentation.customElements.dialogs.ListDialogDto
import com.spector.template.presentation.customElements.lists.FixedListItemDto
import com.spector.template.presentation.main.description.SideEffect
import com.spector.template.presentation.main.description.State

data class SettingsState(
    val model: Model = Model(),
    val list: List<FixedListItemDto> = listOf(),
    val listDialogDto: ListDialogDto = ListDialogDto(),
    val editDialogDto: EditDialogDto = EditDialogDto(),
) : State

sealed class SettingsSideEffect : SideEffect


