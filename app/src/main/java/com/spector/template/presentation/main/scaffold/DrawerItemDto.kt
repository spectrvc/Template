package com.spector.template.presentation.main.scaffold

import com.spector.template.domain.dto.navigation.EmptyOptions
import com.spector.template.domain.enums.ScreensEnum
import com.spector.template.domain.dto.navigation.ScreenOptions

data class DrawerItemDto(
    val id: ScreensEnum,
    val options: ScreenOptions = EmptyOptions(),
    val text: String = "",
    val imageId: Int = 0,
)

