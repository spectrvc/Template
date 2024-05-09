package com.spector.template.presentation.customElements.other

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spector.template.presentation.dp.CardPadding.Companion.CARD_PADDING
import com.spector.template.presentation.dp.ElevationPadding.Companion.ELEVATION_PADDING

@Composable
fun InternalCard(
    content: @Composable () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        //elevation = ELEVATION_PADDING,
        modifier = Modifier
            .padding(horizontal = CARD_PADDING, vertical = ELEVATION_PADDING)
    ) {
        content()
    }
}



