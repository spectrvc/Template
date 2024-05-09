package com.spector.template.data.repository

import com.google.gson.Gson
import com.spector.template.domain.const.ConstOptions.Companion.OPTIONS
import com.spector.template.domain.dto.navigation.EmptyOptions
import com.spector.template.domain.dto.navigation.NavigationDto
import com.spector.template.domain.dto.navigation.ScreenOptions
import com.spector.template.domain.enums.NavigationCommandEnum
import com.spector.template.domain.enums.ScreensEnum
import com.spector.template.domain.repository.NavigationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class NavigationRepositoryImpl : NavigationRepository {
    override val navigationSideEffect = MutableSharedFlow<NavigationDto>()

    override fun navigate(screen: ScreensEnum, options: ScreenOptions) {
        var route = screen.name
        if (options !is EmptyOptions) {
            val gson = Gson()
            val optionsJson: String = gson.toJson(options)
            route = "$route/$OPTIONS=$optionsJson"
        }
        val navigationDto = NavigationDto(
            command = NavigationCommandEnum.NAVIGATE,
            route = route
        )
        coroutineScope.launch(Dispatchers.Main) {
            navigationSideEffect.emit(navigationDto)
        }
    }

    override fun popBack() {
        val navigationDto = NavigationDto(
            command = NavigationCommandEnum.POP_BACK,
            route = ""
        )
        coroutineScope.launch(Dispatchers.Main) {
            navigationSideEffect.emit(navigationDto)
        }
    }

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

}