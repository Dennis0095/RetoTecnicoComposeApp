package com.pe.manager.domain.repository

interface ManagerAppsRepository {
    suspend fun registerApp(): Boolean
}