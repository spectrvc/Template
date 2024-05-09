package com.spector.template.presentation.main.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.spector.template.presentation.main.theme.TemplateTheme
import com.spector.template.presentation.main.theme.templateColors

@Composable
fun MainTheme(viewModel: MainViewModel, content: @Composable () -> Unit) {
    val state by viewModel.observeState().collectAsState()
    TemplateTheme(state.model.useDarkTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.templateColors.iBackgroundMain
        ) {
            content()
        }
    }

}

