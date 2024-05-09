package com.spector.template.presentation.customElements.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spector.template.presentation.customElements.buttons.BottomPanelButton
import com.spector.template.presentation.main.theme.templateColors

data class BottomPanelItemDto(
    val imageId: Int,
    val onClick: () -> Unit,
)

@Composable
fun BottomPanel(
    dtoList: List<BottomPanelItemDto>,
) {
    val panelColor = MaterialTheme.templateColors.iBackgroundPanel
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(panelColor),
        horizontalArrangement = Arrangement.Center
    ) {
        for (itemDtoList in dtoList)
            BottomPanelButton(itemDtoList.imageId) { itemDtoList.onClick() }
    }
}




