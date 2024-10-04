package com.pe.manager.domain.usescase

import com.pe.manager.domain.entity.RegisterApp
import com.pe.manager.domain.repository.ManagerAppsRepository
import javax.inject.Inject

class UpdateApplicationUseCase @Inject constructor(private val repository: ManagerAppsRepository)  {

    suspend operator fun invoke(registerApp: RegisterApp) = repository.updateApp(registerApp)
}