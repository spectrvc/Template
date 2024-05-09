package com.spector.template.presentation.screens.about

import android.content.pm.PackageInfo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spector.template.R
import com.spector.template.presentation.customElements.dividers.MainDivider
import com.spector.template.presentation.customElements.images.StandardImage

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainDivider()
        Column(
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            StandardImage(
                imageId = R.drawable.template,
                size = 96.dp,
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Text(
                text = stringResource(R.string.appName),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "version " + getVersionName(),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
        Spacer(
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun getVersionName(): String {
    val packageManager = LocalContext.current.packageManager
    val packageName = LocalContext.current.packageName
    val packageInfo: PackageInfo = packageManager.getPackageInfo(packageName, 0)
    return packageInfo.versionName
}
