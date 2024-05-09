package com.spector.template.presentation.customElements.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.spector.template.presentation.customElements.dividers.PanelDivider
import com.spector.template.presentation.dp.textPadding.LargeTextPadding.Companion.LARGE_TEXT_PADDING

@Composable
fun DialogHeader(
    text: String,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LARGE_TEXT_PADDING),
        style = MaterialTheme.typography.displayLarge,
        text = text,
        fontWeight = FontWeight.Bold
    )
    PanelDivider()
}




