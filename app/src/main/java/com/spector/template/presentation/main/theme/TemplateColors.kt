package com.spector.template.presentation.main.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

data class TemplateColors(
    val materialColors: ColorScheme,
    val iBackgroundMain: Color,
    val iBackgroundMainGroup: Color,
    val iBackgroundMainSelected: Color,
    val iBackgroundCard: Color,
    val iBackgroundCardGroup: Color,
    val iBackgroundPanel: Color,
    val iTextMain: Color,
    val iTextMainGroup: Color,
    val iTextPanel: Color,
    val iTextRed: Color,
    val iTextGreen: Color,
    val iTextBlue: Color,
    val iDividerMain: Color,
    val iProgressPrimary: Color,
    val iProgressSecondary: Color,
    val iSwitchThumb: Color,
    val iSwitchTrack: Color,
) {
    val primary: Color get() = materialColors.primary
    val onPrimary: Color get() = materialColors.onPrimary
    val primaryContainer: Color get() = materialColors.primaryContainer
    val onPrimaryContainer: Color get() = materialColors.onPrimaryContainer
    val inversePrimary: Color get() = materialColors.inversePrimary
    val secondary: Color get() = materialColors.secondary
    val onSecondary: Color get() = materialColors.onSecondary
    val secondaryContainer: Color get() = materialColors.secondaryContainer
    val onSecondaryContainer: Color get() = materialColors.onSecondaryContainer
    val tertiary: Color get() = materialColors.tertiary
    val onTertiary: Color get() = materialColors.onTertiary
    val tertiaryContainer: Color get() = materialColors.tertiaryContainer
    val onTertiaryContainer: Color get() = materialColors.onTertiaryContainer
    val background: Color get() = materialColors.background
    val onBackground: Color get() = materialColors.onBackground
    val surface: Color get() = materialColors.surface
    val onSurface: Color get() = materialColors.onSurface
    val surfaceVariant: Color get() = materialColors.surfaceVariant
    val onSurfaceVariant: Color get() = materialColors.onSurfaceVariant
    val surfaceTint: Color get() = materialColors.surfaceTint
    val inverseSurface: Color get() = materialColors.inverseSurface
    val inverseOnSurface: Color get() = materialColors.inverseOnSurface
    val error: Color get() = materialColors.error
    val onError: Color get() = materialColors.onError
    val errorContainer: Color get() = materialColors.errorContainer
    val onErrorContainer: Color get() = materialColors.onErrorContainer
    val outline: Color get() = materialColors.outline
    val outlineVariant: Color get() = materialColors.outlineVariant
    val scrim: Color get() = materialColors.scrim
}