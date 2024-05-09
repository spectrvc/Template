package com.spector.template.presentation.main.scaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.spector.template.presentation.main.navigation.NavHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerScaffold(
    topBarTitle: String,
    scope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = topBarTitle,
                openDrawer = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(navController)
        }
    }
}