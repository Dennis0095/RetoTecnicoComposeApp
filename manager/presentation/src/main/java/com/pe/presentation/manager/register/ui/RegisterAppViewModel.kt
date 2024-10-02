package com.pe.presentation.manager.register.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pe.manager.domain.entity.Frequency.Companion.getFrequency
import com.pe.manager.domain.entity.Priority
import com.pe.manager.domain.entity.Priority.Companion.getPriority
import com.pe.manager.domain.entity.RegisterApp
import com.pe.manager.domain.entity.StatusApp.Companion.getStatusApp
import com.pe.manager.domain.entity.TypeApp.Companion.getTypeApp
import com.pe.manager.domain.usescase.RegisterAppUseCase
import com.pe.presentation.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterAppViewModel @Inject constructor(
    private val registerAppUseCase: RegisterAppUseCase
) : BaseViewModel<RegisterAppUiState, RegisterAppIntent, RegisterAppNavigation>() {

    override fun createInitialState(): RegisterAppUiState {
        return RegisterAppUiState()
    }
    override suspend fun handleIntent(intent: RegisterAppIntent) {
        when (intent) {
            RegisterAppIntent.RegisterApp -> registerApp()
            RegisterAppIntent.OnDismissedSelector -> dismissedBottomSheet()
            is RegisterAppIntent.OnFrequencySelected -> {
                setUiState { copy(isFrequencySelected = true, frequency = getFrequency(intent.description)) }
            }
            is RegisterAppIntent.OnTypeAppSelected -> {
                setUiState { copy(isTypeAppSelected = true, typeApp = getTypeApp(intent.description)) }
            }
            is RegisterAppIntent.OnPrioritySelected -> {
                setUiState { copy(isPrioritySelected = true, priority = getPriority(intent.description)) }
            }
            is RegisterAppIntent.OnStatusAppSelected -> {
                setUiState { copy(isStatusSelected = true, statusApp = getStatusApp(intent.description)) }
            }
            RegisterAppIntent.OnShowedFrequencySelector -> setUiState { copy(showFrequencySelector = true) }
            RegisterAppIntent.OnShowedPrioritySelector -> setUiState { copy(showPrioritySelector = true) }
            RegisterAppIntent.OnShowedStatusSelector -> setUiState { copy(showStatusSelector = true) }
            RegisterAppIntent.OnShowedTypeAppSelector -> setUiState { copy(showTypeAppSelector = true) }
            is RegisterAppIntent.UpdateComments -> setUiState { copy(comments = intent.text) }
            is RegisterAppIntent.UpdateCosts -> setUiState { copy(costs = intent.text) }
            is RegisterAppIntent.UpdateCpuResource -> setUiState { copy(cpuResource = intent.text) }
            is RegisterAppIntent.UpdateKeyActivities -> setUiState { copy(keyActivities = intent.text) }
            is RegisterAppIntent.UpdateMemoryResource -> setUiState { copy(memoryResource = intent.text) }
            is RegisterAppIntent.UpdateNetworkConsumptionResource -> setUiState { copy(networkConsumptionResource = intent.text) }
            is RegisterAppIntent.UpdateNumberOfIncidents -> setUiState { copy(numberOfIncidents = intent.text) }
            is RegisterAppIntent.UpdateStorageResource -> setUiState { copy(storageResource = intent.text) }
            is RegisterAppIntent.UpdateTextName -> setUiState { copy(nameApp = intent.text) }
            is RegisterAppIntent.UpdateUsersActive -> setUiState { copy(usersActive = intent.text) }
            is RegisterAppIntent.UpdateVersion -> setUiState { copy(version = intent.text) }
            RegisterAppIntent.OnNavigateToBack -> goNavigation(RegisterAppNavigation.NavigateToBack)
        }
    }

    private fun dismissedBottomSheet(){
        setUiState { copy(showTypeAppSelector = false, showFrequencySelector = false, showPrioritySelector = false, showStatusSelector = false) }
    }

    private fun registerApp(){
        setUiState {copy( showLoadingButton = true) }
        val currentState = uiState.value
        viewModelScope.launch {
            val result = registerAppUseCase(
                RegisterApp(
                    nameApp = currentState.nameApp,
                    version = currentState.version,
                    usersActive = currentState.usersActive,
                    cpuResource = currentState.cpuResource,
                    memoryResource = currentState.memoryResource,
                    storageResource = currentState.storageResource,
                    networkConsumptionResource = currentState.networkConsumptionResource,
                    costs = currentState.costs,
                    comments = currentState.comments,
                    keyActivities = currentState.keyActivities,
                    numberOfIncidents = currentState.numberOfIncidents,
                    typeApp = currentState.typeApp,
                    frequency = currentState.frequency,
                    priority = currentState.priority,
                    statusApp = currentState.statusApp,
                )
            )
            Log.d("DMA_LECTOR", " resultado = " + result)
            setUiState {copy( showLoadingButton = false) }
            goNavigation(RegisterAppNavigation.NavigateToSuccessful)
            resetForm()
        }
    }

    private fun resetForm(){
        setUiState { copy(
            nameApp = "",
            version = "",
            usersActive = "",
            cpuResource = "",
            memoryResource = "",
            storageResource = "",
            networkConsumptionResource = "",
            costs = "",
            comments = "",
            keyActivities = "",
            numberOfIncidents = "",
            isTypeAppSelected = false,
            isFrequencySelected = false,
            isPrioritySelected = false,
            isStatusSelected = false,
            typeApp = null,
            frequency = null,
            priority = null,
            statusApp = null,
        ) }
    }
}