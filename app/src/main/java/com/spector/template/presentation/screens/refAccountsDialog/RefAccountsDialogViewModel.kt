package com.spector.template.presentation.screens.refAccountsDialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spector.template.domain.dto.navigation.RefAccountsDialogOptions
import com.spector.template.domain.dto.sql.RefAccountsDto
import com.spector.template.domain.useCases.NavigationUseCase
import com.spector.template.domain.useCases.RefAccountsUseCase
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
class RefAccountsDialogViewModel @Inject constructor(
    private val refAccountsUseCase: RefAccountsUseCase,
    private val navigationUseCase: NavigationUseCase
) : ViewModel(), Store<RefAccountsDialogState, RefAccountsDialogSideEffect> {

    private val state = MutableStateFlow(RefAccountsDialogState())

    private val sideEffect = MutableSharedFlow<RefAccountsDialogSideEffect>()

    override fun observeState(): StateFlow<RefAccountsDialogState> = state

    override fun observeSideEffect(): SharedFlow<RefAccountsDialogSideEffect> = sideEffect

    fun onInit(options: RefAccountsDialogOptions) {
        if (options.id == 0) {
            state.value = state.value.copy(refAccountsDto = RefAccountsDto())
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                val foundRecord = refAccountsUseCase.getDto(options.id)
                if (foundRecord.id != 0) {
                    state.value = state.value.copy(refAccountsDto = foundRecord)
                }
            }
        }
    }

    fun onClickSave() {
        viewModelScope.launch(Dispatchers.IO) {
            refAccountsUseCase.saveOne(state.value.refAccountsDto)
            hideDialog()
        }
    }

    fun hideDialog() {
        navigationUseCase.popBack()
    }

    fun onChangeName(text: String) {
        val newRefAccounts = state.value.refAccountsDto.copy(name = text)
        state.value = state.value.copy(refAccountsDto = newRefAccounts)
    }

    fun onChangeLogin(text: String) {
        val newRefAccounts = state.value.refAccountsDto.copy(login = text)
        state.value = state.value.copy(refAccountsDto = newRefAccounts)
    }

}

