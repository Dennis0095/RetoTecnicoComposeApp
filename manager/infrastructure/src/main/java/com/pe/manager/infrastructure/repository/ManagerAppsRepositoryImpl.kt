package com.pe.manager.infrastructure.repository

import android.util.Log
import com.pe.manager.domain.repository.ManagerAppsRepository
import com.pe.manager.infrastructure.di.LocalStorage

class ManagerAppsRepositoryImpl(
    private val localStorage: LocalStorage
): ManagerAppsRepository {
    override suspend fun registerApp(): Boolean {
        localStorage.putString("KEY_1", "AAA")
        Log.d("DMA_LECTOR", " guardado = " + localStorage.getString("KEY_1"))
        return true
    }
}