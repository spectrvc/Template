package com.spector.template.data.repository

import android.content.Context
import android.os.Build
import com.spector.template.domain.enums.LanguageEnum
import com.spector.template.domain.repository.DeviceRepository
import java.util.*

class DeviceRepositoryImpl(private val context: Context) : DeviceRepository {

    override fun setLocale(languageEnum: LanguageEnum) {
        //set locale for texts in viewModels (for Composable use another function)
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

    override fun getResourceString(resourceId: Int): String {
        return if (resourceId == 0)
            ""
        else
            context.getString(resourceId)
    }

}