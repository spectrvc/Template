package com.spector.template.presentation.main.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val lightColorPalette = TemplateColors(
    materialColors = lightColorScheme(),
    iBackgroundMain = Color(0xffffffff),
    iBackgroundMainGroup = Color(0xfff2f2f2),
    iBackgroundMainSelected = Color(0xffc3d0e6),
    iBackgroundCard = Color(0xffe1e8f3),
    iBackgroundCardGroup = Color(0xffa1d9f9),
    iBackgroundPanel = Color(0xff5cc9ff),
    iTextMain = Color(0xff000000),
    iTextMainGroup = Color(0xff808080),
    iTextPanel = Color(0xffffffff),
    iTextRed = Color(0xffff0000),
    iTextGreen = Color(0xff009900),
    iTextBlue = Color(0xff4086ff),
    iDividerMain = Color(0xffdcdcdc),
    iProgressPrimary = Color(0xff5cc9ff),
    iProgressSecondary = Color(0xff5cc9ff),
    iSwitchThumb = Color(0xff5cc9ff),
    iSwitchTrack = Color(0xffdcdcdc),
)

private val darkColorPalette = TemplateColors(
    materialColors = darkColorScheme().copy(
        background = Color(0xff353535)
    ),
    iBackgroundMain = Color(0xff353535),
    iBackgroundMainGroup = Color(0xff000000),
    iBackgroundMainSelected = Color(0xff466473),
    iBackgroundCard = Color(0xff5c5c5c),
    iBackgroundCardGroup = Color(0xff466473),
    iBackgroundPanel = Color(0xff306c8a),
    iTextMain = Color(0xffe5e5e5),
    iTextMainGroup = Color(0xff808080),
    iTextPanel = Color(0xffe5e5e5),
    iTextRed = Color(0xffff7e7e),
    iTextGreen = Color(0xff009900),
    iTextBlue = Color(0xff4086ff),
    iDividerMain = Color(0xff808080),
    iProgressPrimary = Color(0xff306c8a),
    iProgressSecondary = Color(0xff306c8a),
    iSwitchThumb = Color(0xff306c8a),
    iSwitchTrack = Color(0xffdcdcdc),
)

private val localColors = staticCompositionLocalOf { lightColorPalette }

@Composable
fun TemplateTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme)
        darkColorPalette
    else
        lightColorPalette
    CompositionLocalProvider(localColors provides colors) {
        MaterialTheme(
            colorScheme = colors.materialColors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

val MaterialTheme.templateColors: TemplateColors
    @Composable
    @ReadOnlyComposable
    get() = localColors.current