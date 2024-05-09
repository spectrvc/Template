package com.spector.template.presentation.screens.refAccountsDialog

import com.spector.template.domain.dto.sql.RefAccountsDto
import com.spector.template.presentation.main.description.SideEffect
import com.spector.template.presentation.main.description.State

data class RefAccountsDialogState(
    val refAccountsDto: RefAccountsDto = RefAccountsDto(),
) : State

sealed class RefAccountsDialogSideEffect : SideEffect
