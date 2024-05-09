package com.spector.template.presentation.customElements.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.spector.template.presentation.customElements.other.InternalCard

@Composable
fun BaseDialog(
    usePlatformDefaultWidth: Boolean, //true = default, false = fullScreen
    onClickCansel: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = usePlatformDefaultWidth),
        onDismissRequest = { onClickCansel() }
    ) {
        InternalCard { content() }
    }
}



