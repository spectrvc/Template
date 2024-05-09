package com.spector.template.di

import com.spector.template.data.repository.*
import com.spector.template.domain.repository.*
import com.spector.template.domain.repository.room.*
import com.spector.template.domain.useCases.singlton.SingletonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Provides
    @Singleton
    fun provideSingletonUseCase(
        settingsRepository: SettingsRepository,
        deviceRepository: DeviceRepository,
    ): SingletonUseCase {
        return SingletonUseCase(
            settingsRepository = settingsRepository,
            deviceRepository = deviceRepository,
        )
    }

}




