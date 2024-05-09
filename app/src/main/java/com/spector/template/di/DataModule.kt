package com.spector.template.di

import android.content.Context
import androidx.room.Room
import com.spector.template.data.repository.*
import com.spector.template.data.repository.room.*
import com.spector.template.data.room.TemplateDatabase
import com.spector.template.data.room.MIGRATION_100_101
import com.spector.template.data.room.refAccounts.RefAccountsDao
import com.spector.template.data.storage.SettingsStorage
import com.spector.template.data.storage.SharedPreferencesSettingsStorage
import com.spector.template.domain.repository.*
import com.spector.template.domain.repository.room.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideSettingsStorage(@ApplicationContext context: Context): SettingsStorage {
        return SharedPreferencesSettingsStorage(context = context)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(settingsStorage: SettingsStorage): SettingsRepository {
        return SettingsRepositoryImpl(settingsStorage = settingsStorage)
    }

    @Provides
    @Singleton
    fun provideDeviceRepository(@ApplicationContext context: Context): DeviceRepository {
        return DeviceRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideNavigationRepository(): NavigationRepository {
        return NavigationRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideTemplateDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        TemplateDatabase::class.java,
        "template_database"
    ).addMigrations(
        MIGRATION_100_101
    ).build()

    @Provides
    @Singleton
    fun provideRefAccountsDao(db: TemplateDatabase) = db.refAccountsDao()

    @Provides
    @Singleton
    fun provideRefAccountsRepository(refAccountsDao: RefAccountsDao): RefAccountsRepository {
        return RefAccountsRepositoryImpl(refAccountsDao = refAccountsDao)
    }


}




