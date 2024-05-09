package com.spector.template.domain.useCases

import com.spector.template.domain.dto.navigation.ScreenOptions
import com.spector.template.domain.enums.ScreensEnum
import com.spector.template.domain.repository.NavigationRepository

class NavigationUseCase(private val navigationRepository: NavigationRepository) {

    val navigationSideEffect = navigationRepository.navigationSideEffect

    fun navigate(screen: ScreensEnum, options: ScreenOptions){
        navigationRepository.navigate(screen, options)
    }

    fun popBack(){
        navigationRepository.popBack()
    }
}