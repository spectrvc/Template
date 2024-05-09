package com.spector.template.presentation.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.spector.template.domain.dto.navigation.EmptyOptions
import com.spector.template.domain.enums.NavigationCommandEnum
import com.spector.template.presentation.main.scaffold.DrawerBody
import com.spector.template.presentation.main.scaffold.DrawerItemDto
import com.spector.template.presentation.main.scaffold.DrawerScaffold
import com.spector.template.presentation.main.screen.MainTheme
import com.spector.template.presentation.main.screen.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme(viewModel) { DrawerNavigationScreen(viewModel) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerNavigationScreen(viewModel: MainViewModel) {
    val state by viewModel.observeState().collectAsState()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    //Implement the navigation of application here
    ImplementNavigation(viewModel, navController)

    //set locale for texts in Composable (for viewModels use another function)
    val context = LocalContext.current
    SetApplicationLocale(viewModel, context)

    //get all related to screen (title)
    var topBarTitle by remember { mutableStateOf("") }
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            CoroutineScope(Dispatchers.IO).launch {
                val currentBarTitle = viewModel.getTitleByRoute(backStackEntry)
                if (currentBarTitle != "")
                    topBarTitle = currentBarTitle
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                content = {
                    DrawerBody(
                        menuItems = state.drawerScreensList,
                        scope = scope,
                        drawerState = drawerState,
                    ) {
                        navHostNavigate(
                            drawerItemDto = it,
                            navController = navController
                        )
                    }
                }
            )
        },
        gesturesEnabled = true,
        content = {
            DrawerScaffold(
                topBarTitle = topBarTitle,
                scope = scope,
                drawerState = drawerState,
                navController = navController,
            )
        }
    )
}

@Composable
private fun SetApplicationLocale(viewModel: MainViewModel, context: Context) {
    LaunchedEffect(Unit) {
        viewModel.observeState().collect { state ->
            val languageEnum = state.model.language
            val locale = Locale(languageEnum.value)
            val resources = context.resources
            val config = resources.configuration
            Locale.setDefault(locale)
            config.setLocale(locale)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                context.createConfigurationContext(config)
            @Suppress("DEPRECATION")
            resources.updateConfiguration(config, resources.displayMetrics)
        }
    }
}

@Composable
private fun ImplementNavigation(viewModel: MainViewModel, navController: NavHostController) {
    LaunchedEffect(Unit) {
        viewModel.observeNavigationSideEffect().collect { navigationDto ->
            when (navigationDto.command) {
                NavigationCommandEnum.NAVIGATE -> navController.navigate(navigationDto.route)
                NavigationCommandEnum.POP_BACK -> navController.popBackStack()
            }
        }
    }
}

private fun navHostNavigate(drawerItemDto: DrawerItemDto, navController: NavHostController) {
    var route = drawerItemDto.id.name
    if (drawerItemDto.options !is EmptyOptions) {
        val gson = Gson()
        val optionsJson: String = gson.toJson(drawerItemDto.options)
        route = "$route/options=$optionsJson"
    }
    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId)
        launchSingleTop = true
    }
}


