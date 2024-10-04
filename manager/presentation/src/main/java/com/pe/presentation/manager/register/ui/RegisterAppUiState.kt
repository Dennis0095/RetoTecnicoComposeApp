package com.pe.presentation.manager.register.ui

import com.pe.manager.domain.entity.Frequency
import com.pe.manager.domain.entity.Priority
import com.pe.manager.domain.entity.StatusApp
import com.pe.manager.domain.entity.TypeApp

data class RegisterAppUiState(
    val idApp: Int? = null,
    val title: String = "",
    val messageSuccessful: String = "",
    val textButton: String = "",
    val nameApp: String = "",
    val version: String = "",
    val usersActive: String = "",
    val costs: String = "",
    val comments: String = "",
    val keyActivities: String = "",
    val numberOfIncidents: String = "",
    var isTypeAppSelected: Boolean = false,
    var showLoadingButton: Boolean = false,
    var isFrequencySelected: Boolean = false,
    var isPrioritySelected: Boolean = false,
    var isStatusSelected: Boolean = false,

    var showTypeAppSelector: Boolean = false,
    var showFrequencySelector: Boolean = false,
    var showPrioritySelector: Boolean = false,
    var showStatusSelector: Boolean = false,

    var typeApp: TypeApp? = null,
    var frequency: Frequency? = null,
    var priority: Priority? = null,
    var statusApp: StatusApp? = null,

    ){
    val isEnabled = nameApp.isNotBlank() && version.isNotBlank() && usersActive.isNotBlank() && costs.isNotBlank() &&
            comments.isNotBlank() && keyActivities.isNotBlank() && numberOfIncidents.isNotBlank() &&
            isTypeAppSelected && isFrequencySelected && isPrioritySelected && isStatusSelected
}

