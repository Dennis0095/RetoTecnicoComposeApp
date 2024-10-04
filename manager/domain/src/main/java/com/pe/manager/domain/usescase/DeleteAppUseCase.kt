package com.pe.manager.domain.usescase

import com.pe.manager.domain.repository.ManagerAppsRepository
import javax.inject.Inject

class DeleteAppUseCase @Inject constructor(private val repository: ManagerAppsRepository) {

    suspend operator fun invoke(id: Int) = repository.deleteApp(id)
}