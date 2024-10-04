package com.pe.presentation.manager.register.ui

sealed class RegisterAppIntent{
    object OnShowedTypeAppSelector : RegisterAppIntent()
    object OnNavigateToBack : RegisterAppIntent()
    object OnShowedFrequencySelector : RegisterAppIntent()
    object OnShowedStatusSelector : RegisterAppIntent()
    object OnShowedPrioritySelector : RegisterAppIntent()
    object RegisterApp : RegisterAppIntent()
    object OnDismissedSelector : RegisterAppIntent()
    data class OnTypeAppSelected(val description: String) : RegisterAppIntent()
    data class ValidateRooted(val idApp: String) : RegisterAppIntent()
    data class OnPrioritySelected(val description: String) : RegisterAppIntent()
    data class OnStatusAppSelected(val description: String) : RegisterAppIntent()
    data class OnFrequencySelected(val description: String) : RegisterAppIntent()
    data class UpdateTextName(val text: String) : RegisterAppIntent()
    data class UpdateVersion(val text: String) : RegisterAppIntent()
    data class UpdateUsersActive(val text: String) : RegisterAppIntent()
    data class UpdateCosts(val text: String) : RegisterAppIntent()
    data class UpdateComments(val text: String) : RegisterAppIntent()
    data class UpdateKeyActivities(val text: String) : RegisterAppIntent()
    data class UpdateNumberOfIncidents(val text: String) : RegisterAppIntent()

}
