package com.spector.template.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.google.gson.Gson
import com.spector.template.domain.const.ConstOptions.Companion.OPTIONS
import com.spector.template.domain.dto.navigation.RefAccountsDialogOptions
import com.spector.template.domain.enums.ScreensEnum
import com.spector.template.presentation.screens.about.AboutScreen
import com.spector.template.presentation.screens.refAccounts.RefAccountsScreen
import com.spector.template.presentation.screens.refAccounts.RefAccountsViewModel
import com.spector.template.presentation.screens.refAccountsDialog.RefAccountsDialogScreen
import com.spector.template.presentation.screens.refAccountsDialog.RefAccountsDialogViewModel
import com.spector.template.presentation.screens.settings.SettingsScreen
import com.spector.template.presentation.screens.settings.SettingsViewModel

@Composable
fun NavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreensEnum.REF_ACCOUNTS.value,
    ) {
        composable(ScreensEnum.REF_ACCOUNTS.value) { backStackEntry ->
            //we must have such a check because the destination may be null when the start destination is changed
            if (navController.currentDestination == null) {
                val viewModel: RefAccountsViewModel = hiltViewModel()
                viewModel.onInit()
                RefAccountsScreen(viewModel)
            } else {
                val owner =
                    remember(backStackEntry) { navController.getBackStackEntry(backStackEntry.destination.parent!!.id) }
                val viewModel: RefAccountsViewModel = hiltViewModel(owner)
                viewModel.onInit()
                RefAccountsScreen(viewModel)
            }
        }
        dialog(ScreensEnum.REF_ACCOUNTS_DIALOG.value) { backStackEntry ->
            val owner =
                remember(backStackEntry) { navController.getBackStackEntry(backStackEntry.destination.parent!!.id) }
            val optionsJson = backStackEntry.arguments?.getString(OPTIONS)
            val options = Gson().fromJson(optionsJson, RefAccountsDialogOptions::class.java)
            val viewModel: RefAccountsDialogViewModel = hiltViewModel(owner)
            viewModel.onInit(options)
            RefAccountsDialogScreen(viewModel)
        }
        composable(ScreensEnum.SETTINGS.value) { backStackEntry ->
            val owner =
                remember(backStackEntry) { navController.getBackStackEntry(backStackEntry.destination.parent!!.id) }
            val viewModel: SettingsViewModel = hiltViewModel(owner)
            SettingsScreen(viewModel)
        }
        composable(ScreensEnum.ABOUT.value) {
            AboutScreen()
        }
    }
}


