package com.spector.template.presentation.screens.refAccountsDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.spector.template.R
import com.spector.template.presentation.customElements.dialogs.BaseDialog
import com.spector.template.presentation.customElements.dialogs.DialogHeader
import com.spector.template.presentation.customElements.other.BottomPanel
import com.spector.template.presentation.customElements.other.BottomPanelItemDto
import com.spector.template.presentation.customElements.other.ScrolledColumn
import com.spector.template.presentation.customElements.spacers.DialogSpacer
import com.spector.template.presentation.customElements.textFields.EditTextField

@Composable
fun RefAccountsDialogScreen(viewModel: RefAccountsDialogViewModel) {
    val state by viewModel.observeState().collectAsState()
    BaseDialog(
        usePlatformDefaultWidth = true,
        onClickCansel = { viewModel.hideDialog() }
    ) {
        var header = stringResource(R.string.refAccountsDialogTitle)
        header += if (state.refAccountsDto.id == 0)
            " (" + stringResource(R.string.refAccountsDialogNew) + ")"
        else
            " (id = " + state.refAccountsDto.id + ")"
        Column {
            DialogHeader(header)
            DialogSpacer()
            ScrolledColumn(
                modifier = Modifier.weight(1f, false)
            ) {
                Centrepiece(state, viewModel)
            }
            val bottomPanelDtoList: MutableList<BottomPanelItemDto> = mutableListOf()
            bottomPanelDtoList.add(BottomPanelItemDto(R.drawable.save) { viewModel.onClickSave() })
            bottomPanelDtoList.add(BottomPanelItemDto(R.drawable.cancel) { viewModel.hideDialog() })
            BottomPanel(bottomPanelDtoList)
        }
    }
}

@Composable
private fun Centrepiece(state: RefAccountsDialogState, viewModel: RefAccountsDialogViewModel) {
    EditTextField(
        title = stringResource(R.string.refAccountsDialogNameLabel),
        text = state.refAccountsDto.name,
        onTextChange = { text -> viewModel.onChangeName(text) },
    )
    EditTextField(
        title = stringResource(R.string.refAccountsDialogLoginLabel),
        text = state.refAccountsDto.login,
        onTextChange = { text -> viewModel.onChangeLogin(text) },
    )
}


