package com.spector.template.presentation.customElements.textFields

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.spector.template.domain.convert.toTemplateDouble
import com.spector.template.presentation.dp.StandardHeight.Companion.STANDARD_HEIGHT
import com.spector.template.presentation.dp.textPadding.MediumTextPadding.Companion.MEDIUM_TEXT_PADDING
import com.spector.template.presentation.main.theme.templateColors
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class, ExperimentalMaterial3Api::class)
@Composable
fun EditTextField(
    modifier: Modifier = Modifier,
    title: String = "",
    text: String = "",
    onTextChange: (String) -> Unit = {},
    onDone: () -> Unit = emptyFun,
    keyboardType: KeyboardType = KeyboardType.Text,
    readOnly: Boolean = false,
    halfPaddingStart: Boolean = false,
    halfPaddingEnd: Boolean = false,
) {
    val paddingStartDenominator = if (halfPaddingStart) 2 else 1
    val paddingEndDenominator = if (halfPaddingEnd) 2 else 1
    var label: @Composable (() -> Unit)? = null
    if (title != "")
        label = { Text(text = title) }
    val keyboardActions = if (onDone == emptyFun)
        KeyboardActions()
    else
        KeyboardActions(
            onDone = {
                onDone()
            }
        )
    val textColorAlpha = if (readOnly) 0.4f else 1f
    val colors = TextFieldDefaults.textFieldColors(
        textColor = MaterialTheme.templateColors.iTextMain.copy(alpha = textColorAlpha),
        disabledTextColor = MaterialTheme.templateColors.iTextMain.copy(alpha = textColorAlpha),
        cursorColor = MaterialTheme.templateColors.iTextMain,
        containerColor = MaterialTheme.templateColors.iBackgroundCard,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedLabelColor = MaterialTheme.templateColors.iTextMain.copy(alpha = textColorAlpha),
        unfocusedLabelColor = MaterialTheme.templateColors.iTextMain.copy(alpha = textColorAlpha),
        disabledLabelColor = MaterialTheme.templateColors.iTextMain.copy(alpha = textColorAlpha),
    )

    //we must use localText because updates to the text field state must be synchronous,
    //we can't have a coroutine updating store and only update the text field after that completes
    var localText by remember { mutableStateOf(text) }
    //let's skip the initial value
    var firstTime = true
    LaunchedEffect(key1 = text) {
        localText = text
        snapshotFlow { localText }
            .debounce(100L)
            .distinctUntilChanged()
            .onEach {
                if (firstTime)
                    firstTime = false
                else
                    onTextChange(it)
            }
            .launchIn(this)
    }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 0.dp,
                bottom = MEDIUM_TEXT_PADDING,
                start = MEDIUM_TEXT_PADDING / paddingStartDenominator,
                end = MEDIUM_TEXT_PADDING / paddingEndDenominator
            )
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
                minHeight = STANDARD_HEIGHT
            ),
        value = localText,
        readOnly = readOnly,
        label = label,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        keyboardActions = keyboardActions,
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = colors,
        onValueChange = {
            if (checkText(keyboardType, it))
                localText = it
        },
        shape = MaterialTheme.shapes.medium,
        singleLine = true,
        trailingIcon = {
            if (localText.isNotEmpty() && !readOnly) {
                IconButton(onClick = { localText = "" }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

fun checkText(keyboardType: KeyboardType, text: String): Boolean {
    if (keyboardType == KeyboardType.Decimal) {
        if (text.isNotEmpty() && text != "0") {
            if (text.length > 10)
                return false
            val double = text.toTemplateDouble()
            if (double == 0.0)
                return false
            if ((double > 9999999) || (double < -9999999))
                return false
        }
    }
    return true
}

val emptyFun: () -> Unit = {}

