package com.spector.template.presentation.screens.refAccounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spector.template.R
import com.spector.template.domain.convert.toTemplateInt
import com.spector.template.domain.dto.common.ItemDto
import com.spector.template.domain.dto.navigation.RefAccountsDialogOptions
import com.spector.template.domain.dto.sql.RefAccountsDto
import com.spector.template.domain.enums.ScreensEnum
import com.spector.template.domain.useCases.NavigationUseCase
import com.spector.template.domain.useCases.RefAccountsUseCase
import com.spector.template.domain.useCases.ResourceUseCase
import com.spector.template.domain.useCases.singlton.SingletonUseCase
import com.spector.template.presentation.customElements.dialogs.QuestionDialogDto
import com.spector.template.presentation.customElements.dialogs.WarningDialogDto
import com.spector.template.presentation.enums.QuestionDialogEnum
import com.spector.template.presentation.main.description.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RefAccountsViewModel @Inject constructor(
    private val refAccountsUseCase: RefAccountsUseCase,
    private val resourceUseCase: ResourceUseCase,
    private val singletonUseCase: SingletonUseCase,
    private val navigationUseCase: NavigationUseCase,
) : ViewModel(), Store<RefAccountsState, RefAccountsSideEffect> {

    private val state = MutableStateFlow(RefAccountsState())

    private val sideEffect = MutableSharedFlow<RefAccountsSideEffect>()

    override fun observeState(): StateFlow<RefAccountsState> = state

    override fun observeSideEffect(): SharedFlow<RefAccountsSideEffect> = sideEffect

    init {
        viewModelScope.launch(Dispatchers.IO) {
            singletonUseCase.model.collect {
                state.value = state.value.copy(model = it)
                convertRecords()
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            refAccountsUseCase.getFlow().collect {
                state.value = state.value.copy(refAccountsDtoList = it)
                convertRecords()
            }
        }
    }

    fun onInit() {
        state.value = state.value.copy(currentRecordId = "")
    }

    private fun convertRecords() {
        state.value =
            state.value.copy(records = state.value.refAccountsDtoList.map { it.toItemDto() })
    }

    fun onClickListItem(itemDto: ItemDto) {
        state.value = state.value.copy(currentRecordId = itemDto.id)
    }

    fun onClickAdd() {
        showDialog(0)
    }

    fun onClickEdit() {
        val accountId = state.value.currentRecordId.toTemplateInt()
        showDialog(accountId)
    }

    fun onClickDelete() {
        val questionDialogDto = QuestionDialogDto(
            id = QuestionDialogEnum.REF_ACCOUNTS_DELETE,
            show = true,
            title = resourceUseCase.getResourceString(R.string.commonWarning),
            text = resourceUseCase.getResourceString(R.string.refAccountsQuestionDelete)
        )
        state.value = state.value.copy(questionDialogDto = questionDialogDto)
    }

    fun onClickQuestionDialogOk(id: QuestionDialogEnum) {
        when (id) {
            QuestionDialogEnum.REF_ACCOUNTS_DELETE -> {
                deleteRecord()
            }

            else -> {}
        }
        hideQuestionDialog()
    }

    private fun deleteRecord() {
        viewModelScope.launch(Dispatchers.IO) {
            val accountId = state.value.currentRecordId.toTemplateInt()
            refAccountsUseCase.deleteOne(accountId)
            state.value = state.value.copy(currentRecordId = "")
        }
    }

    fun hideQuestionDialog() {
        state.value = state.value.copy(questionDialogDto = QuestionDialogDto())
    }

    fun hideWarningDialog() {
        state.value = state.value.copy(warningDialogDto = WarningDialogDto())
    }

    private fun showDialog(id: Int) {
        navigationUseCase.navigate(
            screen = ScreensEnum.REF_ACCOUNTS_DIALOG,
            options = RefAccountsDialogOptions(id = id)
        )
    }

    private fun RefAccountsDto.toItemDto(): ItemDto {
        return ItemDto(
            id = this.id.toString(),
            name = this.name
        )
    }

}

