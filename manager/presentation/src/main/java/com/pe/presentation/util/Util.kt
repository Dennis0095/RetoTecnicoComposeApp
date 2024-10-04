package com.pe.presentation.util

import com.pe.manager.domain.entity.Frequency
import com.pe.manager.domain.entity.Priority
import com.pe.manager.domain.entity.RegisterApp
import com.pe.manager.domain.entity.StatusApp
import com.pe.manager.domain.entity.TypeApp
import com.pe.presentation.manager.apps.ui.detail.DetailAppUiState

val registerApp = DetailAppUiState(
    appRegistered = RegisterApp(
        typeApp = TypeApp.FINANCE,
        nameApp = "Banca cap",
        idApp = 1,
        statusApp = StatusApp.OBSOLETE,
        priority = Priority.LOW,
        version = "1.00",
        comments = "Por el momento esta app no tiene incidencias puesto que recién hace una semana se está usando.",
        costs = "50000",
        frequency = Frequency.MONTHLY,
        keyActivities = "Ingreso de información importante para gerencia y marketing. ingreso de cuestionarios y encuestas",
        numberOfIncidents = "30",
        recommendations = "Se recomienda que esta app debe de eliminarse puesto que no se usa mucho",
        usersActive = "1"
    )
)

val maxIncidents = 20
val minActiveUsers = 10
val maxCost = 1000000.00
val maxCostInactive = 1000.00