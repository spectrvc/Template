package com.spector.template.presentation.customElements.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import com.spector.template.R
import com.spector.template.presentation.dp.StandardHeight

@Composable
fun StandardImage(
    modifier: Modifier = Modifier,
    imageId: Int = R.drawable.attention,
    size: Dp = StandardHeight.STANDARD_HEIGHT,
) {
    Image(
        bitmap = ImageBitmap.imageResource(
            LocalContext.current.resources,
            imageId
        ),
        contentDescription = null,
        modifier = modifier.size(size)
    )
}
