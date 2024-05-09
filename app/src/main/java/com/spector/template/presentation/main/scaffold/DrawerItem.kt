package com.spector.template.presentation.main.scaffold

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spector.template.domain.enums.ScreensEnum
import com.spector.template.presentation.customElements.dividers.MainDivider
import com.spector.template.presentation.customElements.images.StandardImage
import com.spector.template.presentation.dp.textPadding.LargeTextPadding.Companion.LARGE_TEXT_PADDING
import com.spector.template.presentation.main.theme.templateColors

@Composable
fun DrawerItem(
    drawerItemDto: DrawerItemDto,
    modifier: Modifier = Modifier,
    onItemClick: (DrawerItemDto) -> Unit
) {
    when (drawerItemDto.id) {
        ScreensEnum.DIVIDER -> MainDivider()
        ScreensEnum.TITLE -> DrawerTitle(drawerItemDto)
        else -> DriverMenu(drawerItemDto, modifier, onItemClick)
    }
}

@Composable
fun DrawerTitle(drawerItemDto: DrawerItemDto) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        MainDivider(
            modifier = Modifier.weight(1f)
        )
        Text(
            text = " " + drawerItemDto.text + " ",
            color = MaterialTheme.templateColors.iTextMainGroup,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        MainDivider(
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DriverMenu(
    drawerItemDto: DrawerItemDto,
    modifier: Modifier,
    onItemClick: (DrawerItemDto) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(drawerItemDto) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(LARGE_TEXT_PADDING)
        ) {
            StandardImage(
                imageId = drawerItemDto.imageId,
                size = 30.dp,
            )
            Text(
                text = drawerItemDto.text,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(horizontal = LARGE_TEXT_PADDING)
            )
        }
    }
}