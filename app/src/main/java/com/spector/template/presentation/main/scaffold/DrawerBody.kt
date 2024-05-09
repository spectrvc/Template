package com.spector.template.presentation.main.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spector.template.R
import com.spector.template.presentation.dp.textPadding.LargeTextPadding.Companion.LARGE_TEXT_PADDING
import com.spector.template.presentation.main.theme.templateColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerBody(
    menuItems: List<DrawerItemDto>,
    scope: CoroutineScope,
    drawerState: DrawerState,
    modifier: Modifier = Modifier,
    onItemClick: (DrawerItemDto) -> Unit
) {
    Column {
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_TEXT_PADDING)
                .background(MaterialTheme.templateColors.iBackgroundPanel)
                .height(70.dp)
        ) {
            Text(
                text = stringResource(R.string.appName),
                color = MaterialTheme.templateColors.iTextPanel,
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(LARGE_TEXT_PADDING)
            )
        }
        LazyColumn(
            modifier = modifier.weight(1f)
        ) {
            items(menuItems) { item ->
                DrawerItem(
                    item,
                    modifier = modifier
                ) {
                    scope.launch {
                        drawerState.close()
                    }
                    onItemClick(item)
                }
            }
        }
    }
}