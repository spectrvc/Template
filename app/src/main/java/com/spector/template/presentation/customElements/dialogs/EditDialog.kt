package com.spector.template.presentation.customElements.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import com.spector.template.presentation.customElements.other.BottomPanel
import com.spector.template.presentation.customElements.other.BottomPanelItemDto
import com.spector.template.presentation.customElements.spacers.DialogSpacer
import com.spector.template.presentation.customElements.textFields.EditTextField
import com.spector.template.presentation.enums.EditDialogEnum
import com.spector.template.R

data class EditDialogDto(
    val id: EditDialogEnum = EditDialogEnum.EMPTY,
    val show: Boolean = false,
    val title: String = "",
    val text: String = "",
)

@Composable
fun EditDialog(
    editDialogDto: EditDialogDto,
    onClickOk: (EditDialogEnum, String) -> Unit,
    onClickCansel: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    text = editDialogDto.text
    if (editDialogDto.show) {
        val focusRequester = remember { FocusRequester() }
        val modifier = Modifier.focusRequester(focusRequester)
        BaseDialog(
            usePlatformDefaultWidth = true,
            onClickCansel = { onClickCansel() }
        ) {
            Column {
                DialogHeader(editDialogDto.title)
                DialogSpacer()
                EditTextField(
                    modifier = modifier,
                    text = text,
                    onTextChange = { text = it },
                    onDone = { onClickOk(editDialogDto.id, text) },
                )
                val bottomPanelDtoList: List<BottomPanelItemDto> = listOf(
                    BottomPanelItemDto(R.drawable.save) { onClickOk(editDialogDto.id, text) },
                    BottomPanelItemDto(R.drawable.cancel) { onClickCansel() }
                )
                BottomPanel(bottomPanelDtoList)
            }
        }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}



