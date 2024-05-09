package com.spector.template.presentation.customElements.dividers

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spector.template.presentation.main.theme.templateColors


@Composable
fun MainDivider(
    modifier: Modifier = Modifier
) {
    Divider(
        color = MaterialTheme.templateColors.iDividerMain,
        modifier = modifier.fillMaxWidth(),
    )
}



