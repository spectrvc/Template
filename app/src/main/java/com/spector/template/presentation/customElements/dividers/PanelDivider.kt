package com.spector.template.presentation.customElements.dividers

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spector.template.presentation.main.theme.templateColors

@Composable
fun PanelDivider() {
    Divider(
        color = MaterialTheme.templateColors.iBackgroundPanel,
        modifier = Modifier.fillMaxWidth(),
    )
}



