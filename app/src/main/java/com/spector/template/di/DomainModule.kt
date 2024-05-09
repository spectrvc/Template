package com.spector.template.di

import com.spector.template.domain.repository.*
import com.spector.template.domain.repository.room.*
import com.spector.template.domain.useCases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideResourceUseCase(deviceRepository: DeviceRepository): ResourceUseCase {
        return ResourceUseCase(deviceRepository = deviceRepository)
    }

    @Provides
    fun provideRefAccountsUseCase(refAccountsRepository: RefAccountsRepository): RefAccountsUseCase {
        return RefAccountsUseCase(refAccountsRepository = refAccountsRepository)
    }

    @Provides
    fun provideNavigationUseCase(navigationRepository: NavigationRepository): NavigationUseCase {
        return NavigationUseCase(navigationRepository = navigationRepository)
    }

}
