package com.spector.template.presentation.customElements.spacers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spector.template.presentation.dp.textPadding.MediumTextPadding.Companion.MEDIUM_TEXT_PADDING

@Composable
fun DialogSpacer() {
    Spacer(
        modifier = Modifier
            .height(MEDIUM_TEXT_PADDING)
            .fillMaxWidth()
    )
}



