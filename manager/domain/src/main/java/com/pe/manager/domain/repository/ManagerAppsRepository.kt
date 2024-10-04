package com.pe.manager.domain.repository

import com.pe.manager.domain.entity.RegisterApp

interface ManagerAppsRepository {
    suspend fun registerApp(registerApp: RegisterApp): Boolean
    suspend fun updateApp(registerApp: RegisterApp): Boolean
    suspend fun getApps(): ArrayList<RegisterApp>?
    suspend fun getApp(id: Int): RegisterApp?
    suspend fun deleteApp(id: Int): Boolean
}