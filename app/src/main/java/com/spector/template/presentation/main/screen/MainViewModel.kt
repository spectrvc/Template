package com.spector.template.presentation.main.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import com.spector.template.R
import com.spector.template.domain.enums.ScreensEnum
import com.spector.template.domain.useCases.NavigationUseCase
import com.spector.template.domain.useCases.ResourceUseCase
import com.spector.template.domain.useCases.singlton.SingletonUseCase
import com.spector.template.presentation.main.description.Store
import com.spector.template.presentation.main.scaffold.DrawerItemDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val singletonUseCase: SingletonUseCase,
    private val resourceUseCase: ResourceUseCase,
    private val navigationUseCase: NavigationUseCase,
) : ViewModel(), Store<MainState, MainSideEffect> {

    private val state = MutableStateFlow(MainState())

    private val sideEffect = MutableSharedFlow<MainSideEffect>()

    override fun observeState(): StateFlow<MainState> = state

    override fun observeSideEffect(): SharedFlow<MainSideEffect> = sideEffect

    fun observeNavigationSideEffect() = navigationUseCase.navigationSideEffect.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            singletonUseCase.init()
        }
        viewModelScope.launch(Dispatchers.IO) {
            singletonUseCase.model.collect {
                state.value = state.value.copy(model = it)
                updateDrawerScreens()
            }
        }
    }

    private fun updateDrawerScreens() {
        viewModelScope.launch(Dispatchers.IO) {
            val drawerScreensList = mutableListOf<DrawerItemDto>()
            drawerScreensList.add(
                DrawerItemDto(
                    id = ScreensEnum.REF_ACCOUNTS,
                    text = resourceUseCase.getResourceString(R.string.menuRefAccounts),
                    imageId = R.drawable.accounts
                )
            )
            drawerScreensList.add(
                DrawerItemDto(
                    id = ScreensEnum.SETTINGS,
                    text = resourceUseCase.getResourceString(R.string.menuSettings),
                    imageId = R.drawable.settings
                )
            )
            drawerScreensList.add(
                DrawerItemDto(
                    id = ScreensEnum.ABOUT,
                    text = resourceUseCase.getResourceString(R.string.menuAbout),
                    imageId = R.drawable.template
                )
            )
            state.value = state.value.copy(drawerScreensList = drawerScreensList)
        }
    }

    //All dialogs should return an empty string so that the title doesn't change
    fun getTitleByRoute(backStackEntry: NavBackStackEntry): String {
        return when (backStackEntry.destination.route) {
            ScreensEnum.REF_ACCOUNTS.value -> resourceUseCase.getResourceString(R.string.menuRefAccounts)
            ScreensEnum.REF_ACCOUNTS_DIALOG.value -> ""
            ScreensEnum.SETTINGS.value -> resourceUseCase.getResourceString(R.string.menuSettings)
            ScreensEnum.ABOUT.value -> resourceUseCase.getResourceString(R.string.menuAbout)
            else -> resourceUseCase.getResourceString(R.string.appName)
        }
    }

}