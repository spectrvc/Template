package com.spector.template.presentation.customElements.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spector.template.domain.enums.FixedListEnum
import com.spector.template.domain.enums.FixedListTypeEnum
import com.spector.template.presentation.customElements.dividers.MainDivider
import com.spector.template.presentation.dp.textPadding.LargeTextPadding.Companion.LARGE_TEXT_PADDING
import com.spector.template.presentation.dp.textPadding.VerticalTextPadding.Companion.VERTICAL_TEXT_PADDING
import com.spector.template.presentation.main.theme.templateColors

data class FixedListItemDto(
    val id: FixedListEnum = FixedListEnum.EMPTY,
    val type: FixedListTypeEnum = FixedListTypeEnum.STRING,
    val title: String = "",
    val text: String = "",
    val bool: Boolean = false,
    val redColor: Boolean = false,
)

@Composable
fun FixedList(
    list: List<FixedListItemDto>,
    isCard: Boolean = false,
    onClickListItem: (listItemDto: FixedListItemDto) -> Unit
) {
    val backgroundColor = if (isCard)
        MaterialTheme.templateColors.iBackgroundCard
    else
        MaterialTheme.templateColors.iBackgroundMain
    LazyColumn {
        itemsIndexed(list) { index, listItemDto ->
            if (listItemDto.type == FixedListTypeEnum.TITLE)
                Text(
                    text = "${listItemDto.title}:",
                    color = MaterialTheme.templateColors.iTextMainGroup,
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.templateColors.iBackgroundMainGroup)
                        .padding(horizontal = LARGE_TEXT_PADDING, vertical = VERTICAL_TEXT_PADDING),
                )
            else
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .clickable { onClickListItem(listItemDto) }
                )
                {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(LARGE_TEXT_PADDING)
                    ) {
                        val textColor = if (listItemDto.redColor)
                            MaterialTheme.templateColors.iTextRed
                        else
                            MaterialTheme.templateColors.iTextMain
                        Text(
                            text = "${listItemDto.title}:",
                            color = textColor,
                            style = MaterialTheme.typography.displayLarge,
                        )
                        Text(
                            text = listItemDto.text,
                            color = MaterialTheme.templateColors.iTextBlue,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (listItemDto.type == FixedListTypeEnum.BOOLEAN) {
                        Switch(
                            modifier = Modifier.padding(LARGE_TEXT_PADDING),
                            checked = listItemDto.bool,
                            onCheckedChange = {
                                onClickListItem(listItemDto)
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.templateColors.iSwitchThumb,
                                uncheckedThumbColor = MaterialTheme.templateColors.iSwitchThumb,
                                checkedTrackColor = MaterialTheme.templateColors.iSwitchTrack,
                                uncheckedTrackColor = MaterialTheme.templateColors.iSwitchTrack,
                            )

                        )
                    }
                }
            if (!isCard || (index != list.size - 1))
                MainDivider()
        }
    }
}

