package com.spector.template.presentation.customElements.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.spector.template.domain.dto.common.ItemDto
import com.spector.template.presentation.customElements.dividers.MainDivider
import com.spector.template.presentation.customElements.other.BottomPanel
import com.spector.template.presentation.customElements.other.BottomPanelItemDto
import com.spector.template.presentation.customElements.other.InternalCard
import com.spector.template.presentation.customElements.spacers.DialogSpacer
import com.spector.template.presentation.dp.StandardHeight.Companion.STANDARD_HEIGHT
import com.spector.template.presentation.dp.textPadding.LargeTextPadding.Companion.LARGE_TEXT_PADDING
import com.spector.template.presentation.enums.ListDialogEnum
import com.spector.template.presentation.main.theme.templateColors
import com.spector.template.R

data class ListDialogDto(
    val id: ListDialogEnum = ListDialogEnum.EMPTY,
    val show: Boolean = false,
    val title: String = "",
    val list: List<ItemDto> = listOf(),
)

@Composable
fun ListDialog(
    listDialogDto: ListDialogDto,
    onClick: (ListDialogEnum, String) -> Unit,
    onClickCansel: () -> Unit
) {
    if (listDialogDto.show) {
        BaseDialog(
            usePlatformDefaultWidth = true,
            onClickCansel = { onClickCansel() }
        ) {
            Column {
                DialogHeader(listDialogDto.title)
                Column(
                    modifier = Modifier.weight(weight = 1f, fill = false),
                ) {
                    DialogSpacer()
                    InternalCard {
                        LazyColumn(
                            modifier = Modifier
                                .background(MaterialTheme.templateColors.iBackgroundCard)
                        ) {
                            itemsIndexed(listDialogDto.list) { index, item ->
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(STANDARD_HEIGHT)
                                        .clickable { onClick(listDialogDto.id, item.id) }
                                        .padding(LARGE_TEXT_PADDING)
                                ) {
                                    Text(
                                        text = item.name,
                                        color = MaterialTheme.templateColors.iTextMain,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                                if (index != listDialogDto.list.size - 1)
                                    MainDivider()
                            }
                        }
                    }
                    DialogSpacer()
                }
                val bottomPanelDtoList: List<BottomPanelItemDto> = listOf(
                    BottomPanelItemDto(R.drawable.cancel) { onClickCansel() }
                )
                BottomPanel(bottomPanelDtoList)
            }
        }
    }

}



