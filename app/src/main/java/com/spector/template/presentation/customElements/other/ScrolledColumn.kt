package com.spector.template.presentation.customElements.other

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.spector.template.presentation.main.theme.templateColors

@Composable
fun ScrolledColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState),
        ) {
            content()
        }
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = null,
            modifier = Modifier.graphicsLayer {
                // Hide the icon if we cannot scroll forward (we are the end of the list)
                // We use graphicsLayer here to control the alpha so that we only redraw when this
                // value changes, instead of recomposing
                alpha = if (scrollState.canScrollForward) 1f else 0f
            },
            tint = MaterialTheme.templateColors.iTextMain
        )
    }
}



