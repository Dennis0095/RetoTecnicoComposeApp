package com.pe.manager.domain.repository

import com.pe.manager.domain.entity.RegisterApp

interface ManagerAppsRepository {
    suspend fun registerApp(registerApp: RegisterApp): Boolean
}