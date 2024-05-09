package com.spector.template.presentation.main.screen

import com.spector.template.domain.dto.models.Model
import com.spector.template.presentation.main.description.SideEffect
import com.spector.template.presentation.main.description.State
import com.spector.template.presentation.main.scaffold.DrawerItemDto

data class MainState(
    val model: Model = Model(),
    val drawerScreensList: List<DrawerItemDto> = listOf(),
) : State

sealed class MainSideEffect : SideEffect