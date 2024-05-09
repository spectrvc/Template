package com.spector.template.presentation.customElements.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.spector.template.presentation.customElements.images.StandardImage
import com.spector.template.presentation.customElements.other.BottomPanel
import com.spector.template.presentation.customElements.other.BottomPanelItemDto
import com.spector.template.presentation.dp.textPadding.LargeTextPadding.Companion.LARGE_TEXT_PADDING
import com.spector.template.presentation.enums.QuestionDialogEnum
import com.spector.template.R

data class QuestionDialogDto(
    val id: QuestionDialogEnum = QuestionDialogEnum.EMPTY,
    val show: Boolean = false,
    val title: String = "",
    val text: String = "",
)

@Composable
fun QuestionDialog(
    questionDialogDto: QuestionDialogDto,
    onClickOk: (QuestionDialogEnum) -> Unit, //id
    onClickCansel: () -> Unit
) {
    if (questionDialogDto.show) {
        BaseDialog(
            usePlatformDefaultWidth = true,
            onClickCansel = { onClickCansel() }
        ) {
            Column {
                DialogHeader(questionDialogDto.title)
                Row {
                    StandardImage(
                        modifier = Modifier
                            .padding(LARGE_TEXT_PADDING)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(LARGE_TEXT_PADDING),
                        style = MaterialTheme.typography.bodyMedium,
                        text = questionDialogDto.text,
                        fontWeight = FontWeight.Bold
                    )
                }
                val bottomPanelDtoList: List<BottomPanelItemDto> = listOf(
                    BottomPanelItemDto(R.drawable.save) { onClickOk(questionDialogDto.id) },
                    BottomPanelItemDto(R.drawable.cancel) { onClickCansel() }
                )
                BottomPanel(bottomPanelDtoList)
            }
        }
    }
}



