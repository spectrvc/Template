package com.spector.template.presentation.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.spector.template.presentation.customElements.dialogs.EditDialog
import com.spector.template.presentation.customElements.dialogs.ListDialog
import com.spector.template.presentation.customElements.dividers.MainDivider
import com.spector.template.presentation.customElements.lists.FixedList

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val state by viewModel.observeState().collectAsState()
    Column {
        MainDivider()
        FixedList(
            list = state.list,
            onClickListItem = { listItemDto -> viewModel.onSelectListItem(listItemDto) }
        )
    }
    ListDialog(
        listDialogDto = state.listDialogDto,
        onClick = { dialogId, itemId ->
            viewModel.onSelectListDialogItem(
                dialogId,
                itemId
            )
        },
        onClickCansel = { viewModel.hideListDialog() }
    )
    EditDialog(
        editDialogDto = state.editDialogDto,
        onClickOk = { id, value ->
            viewModel.onClickEditDialogOk(
                id,
                value
            )
        },
        onClickCansel = { viewModel.hideEditDialog() }
    )

}


