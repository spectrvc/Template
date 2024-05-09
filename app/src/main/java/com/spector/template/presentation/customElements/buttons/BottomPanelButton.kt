package com.spector.template.presentation.customElements.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spector.template.presentation.customElements.images.StandardImage
import com.spector.template.presentation.dp.textPadding.MediumTextPadding.Companion.MEDIUM_TEXT_PADDING

@Composable
fun BottomPanelButton(
    image: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.padding(all = MEDIUM_TEXT_PADDING)
    ) {
        IconButton(
            onClick = { onClick() },
        ) {
            StandardImage(imageId = image)
        }
    }
}



