package com.spector.template.presentation.screens.refAccounts

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.spector.template.domain.dto.common.ItemDto
import com.spector.template.presentation.customElements.dialogs.QuestionDialog
import com.spector.template.presentation.customElements.dividers.MainDivider
import com.spector.template.presentation.customElements.other.BottomPanel
import com.spector.template.presentation.customElements.other.BottomPanelItemDto
import com.spector.template.presentation.dp.textPadding.LargeTextPadding.Companion.LARGE_TEXT_PADDING
import com.spector.template.presentation.main.theme.templateColors
import com.spector.template.presentation.customElements.dialogs.WarningDialog
import com.spector.template.R

@Composable
fun RefAccountsScreen(viewModel: RefAccountsViewModel) {

    val state by viewModel.observeState().collectAsState()

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.observeSideEffect().collect { sideEffect ->
            when (sideEffect) {
                is RefAccountsSideEffect.ShowToast -> {
                    Toast.makeText(context, sideEffect.text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        MainDivider()
        if (state.records.isNotEmpty())
            Box(modifier = Modifier.weight(1f)) {
                RefAccountsList(
                    list = state.records,
                    currentRecordId = state.currentRecordId,
                    onClickListItem = { referenceListItemDto ->
                        viewModel.onClickListItem(
                            referenceListItemDto
                        )
                    }
                )
            }
        val bottomPanelDtoList =
            mutableListOf(BottomPanelItemDto(R.drawable.add) { viewModel.onClickAdd() })
        if (state.currentRecordId != "") {
            bottomPanelDtoList.add(BottomPanelItemDto(R.drawable.edit) { viewModel.onClickEdit() })
            bottomPanelDtoList.add(BottomPanelItemDto(R.drawable.delete) { viewModel.onClickDelete() })
        }
        BottomPanel(bottomPanelDtoList)
        QuestionDialog(
            questionDialogDto = state.questionDialogDto,
            onClickOk = { id -> viewModel.onClickQuestionDialogOk(id) },
            onClickCansel = { viewModel.hideQuestionDialog() }
        )
        WarningDialog(
            warningDialogDto = state.warningDialogDto,
            onClickCansel = { viewModel.hideWarningDialog() }
        )
    }

}

@Composable
fun RefAccountsList(
    list: List<ItemDto>,
    currentRecordId: String,
    onClickListItem: (itemDto: ItemDto) -> Unit
) {
    LazyColumn {
        itemsIndexed(list) { _, listItemReferenceDto ->
            var bgColor = MaterialTheme.templateColors.iBackgroundMain
            if (listItemReferenceDto.id == currentRecordId)
                bgColor = MaterialTheme.templateColors.iBackgroundMainSelected
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(bgColor)
                    .clickable { onClickListItem(listItemReferenceDto) }
            )
            {
                Column(
                    modifier = Modifier
                        .padding(LARGE_TEXT_PADDING)
                ) {
                    Text(
                        text = listItemReferenceDto.name,
                        color = MaterialTheme.templateColors.iTextMain,
                        style = MaterialTheme.typography.displayLarge
                    )
                }
            }
            MainDivider()
        }
    }
}




