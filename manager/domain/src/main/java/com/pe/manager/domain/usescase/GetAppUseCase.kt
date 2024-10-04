package com.pe.manager.domain.usescase

import com.pe.manager.domain.repository.ManagerAppsRepository
import javax.inject.Inject

class GetAppUseCase @Inject constructor(private val repository: ManagerAppsRepository) {

    suspend operator fun invoke(id: Int) = repository.getApp(id)
}