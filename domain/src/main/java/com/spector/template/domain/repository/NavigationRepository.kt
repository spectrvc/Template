package com.spector.template.domain.repository

import com.spector.template.domain.dto.navigation.NavigationDto
import com.spector.template.domain.dto.navigation.ScreenOptions
import com.spector.template.domain.enums.ScreensEnum
import kotlinx.coroutines.flow.MutableSharedFlow

interface NavigationRepository {

    val navigationSideEffect: MutableSharedFlow<NavigationDto>

    fun navigate(screen: ScreensEnum, options: ScreenOptions)

    fun popBack()

}