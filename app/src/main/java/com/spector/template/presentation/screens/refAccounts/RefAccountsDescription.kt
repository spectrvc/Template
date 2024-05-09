package com.spector.template.presentation.screens.refAccounts

import com.spector.template.domain.dto.common.ItemDto
import com.spector.template.domain.dto.models.Model
import com.spector.template.domain.dto.sql.RefAccountsDto
import com.spector.template.presentation.customElements.dialogs.QuestionDialogDto
import com.spector.template.presentation.customElements.dialogs.WarningDialogDto
import com.spector.template.presentation.main.description.SideEffect
import com.spector.template.presentation.main.description.State

data class RefAccountsState(
    val model: Model = Model(),
    val refAccountsDtoList: List<RefAccountsDto> = listOf(),
    val records: List<ItemDto> = listOf(),
    val currentRecordId: String = "",
    val questionDialogDto: QuestionDialogDto = QuestionDialogDto(),
    val warningDialogDto: WarningDialogDto = WarningDialogDto(),
) : State

sealed class RefAccountsSideEffect : SideEffect {
    class ShowToast(val text: String) : RefAccountsSideEffect()
}

