package com.spector.template.domain.dto.navigation

import com.spector.template.domain.enums.NavigationCommandEnum

data class NavigationDto(
    val command: NavigationCommandEnum = NavigationCommandEnum.NAVIGATE,
    val route: String = ""
)
