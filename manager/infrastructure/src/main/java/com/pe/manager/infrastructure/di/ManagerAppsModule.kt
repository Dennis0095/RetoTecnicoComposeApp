package com.pe.manager.infrastructure.di

import com.pe.manager.domain.repository.ManagerAppsRepository
import com.pe.manager.infrastructure.repository.ManagerAppsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ManagerAppsModule {

    @Singleton
    @Provides
    fun managerAppsRepositoryImpl(
        localStorage: LocalStorage
    ): ManagerAppsRepository {
        return ManagerAppsRepositoryImpl(localStorage)
    }
}