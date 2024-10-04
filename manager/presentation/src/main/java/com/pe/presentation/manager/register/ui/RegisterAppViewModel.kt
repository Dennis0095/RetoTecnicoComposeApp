package com.pe.presentation.manager.register.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pe.manager.domain.entity.Frequency.Companion.getFrequency
import com.pe.manager.domain.entity.Priority.Companion.getPriority
import com.pe.manager.domain.entity.RegisterApp
import com.pe.manager.domain.entity.StatusApp.Companion.getStatusApp
import com.pe.manager.domain.entity.TypeApp.Companion.getTypeApp
import com.pe.manager.domain.usescase.GetAppUseCase
import com.pe.manager.domain.usescase.GetAppsUseCase
import com.pe.manager.domain.usescase.RegisterAppUseCase
import com.pe.manager.domain.usescase.UpdateApplicationUseCase
import com.pe.presentation.util.BaseViewModel
import com.pe.presentation.util.recommendDeleting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterAppViewModel @Inject constructor(
    private val registerAppUseCase: RegisterAppUseCase,
    private val updateApplicationUseCase: UpdateApplicationUseCase,
    private val getAppUseCase: GetAppUseCase,
    private val getAppsUseCase: GetAppsUseCase
) : BaseViewModel<RegisterAppUiState, RegisterAppIntent, RegisterAppNavigation>() {

    override fun createInitialState(): RegisterAppUiState {
        return RegisterAppUiState()
    }
    override suspend fun handleIntent(intent: RegisterAppIntent) {
        when (intent) {
            RegisterAppIntent.RegisterApp -> {
                setUiState {copy( showLoadingButton = true) }
                if(uiState.value.textButton == "Guardar"){
                    registerApp()
                }else{
                    updateApp()
                }

            }
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
            is RegisterAppIntent.UpdateKeyActivities -> setUiState { copy(keyActivities = intent.text) }
            is RegisterAppIntent.UpdateNumberOfIncidents -> setUiState { copy(numberOfIncidents = intent.text) }
            is RegisterAppIntent.UpdateTextName -> setUiState { copy(nameApp = intent.text) }
            is RegisterAppIntent.UpdateUsersActive -> setUiState { copy(usersActive = intent.text) }
            is RegisterAppIntent.UpdateVersion -> setUiState { copy(version = intent.text) }
            RegisterAppIntent.OnNavigateToBack -> goNavigation(RegisterAppNavigation.NavigateToBack)
            is RegisterAppIntent.ValidateRooted -> getApp(intent.idApp)
        }
    }

    private fun getApp(idApp: String){
        if (idApp.toInt() != 999999){
            setUiState { copy(title = "Editar aplicación", textButton = "Actualizar") }
            viewModelScope.launch {
                val result = getAppUseCase(idApp.toInt())
                result?.let {
                    setUiState { copy(
                        nameApp = result.nameApp,
                        version = result.version,
                        usersActive = result.usersActive,
                        costs = result.costs,
                        comments = result.comments,
                        keyActivities = result.keyActivities,
                        numberOfIncidents = result.numberOfIncidents,
                        isTypeAppSelected = true,
                        isFrequencySelected = true,
                        isPrioritySelected = true,
                        isStatusSelected = true,
                        typeApp = result.typeApp,
                        frequency = result.frequency,
                        priority = result.priority,
                        statusApp = result.statusApp,
                        idApp = result.idApp
                    ) }
                }

            }
        }else{
            setUiState { copy(title = "Registrar aplicación", textButton = "Guardar") }
        }

    }

    private fun dismissedBottomSheet(){
        setUiState { copy(showTypeAppSelector = false, showFrequencySelector = false, showPrioritySelector = false, showStatusSelector = false) }
    }

    private fun updateApp(){
        viewModelScope.launch{
            val app = getAppRegistered().copy(idApp = uiState.value.idApp ?: 0)

            val result = updateApplicationUseCase(app)

            setUiState {copy( showLoadingButton = false, messageSuccessful = "La aplicación ha sido actualizada con éxito.") }
            goNavigation(RegisterAppNavigation.NavigateToSuccessful)
            resetForm()
        }
    }
    private fun registerApp(){

        viewModelScope.launch {
            val app = getAppRegistered()
            val result = registerAppUseCase(app)

            setUiState {copy( showLoadingButton = false, messageSuccessful = "La aplicación se ha registrado con éxito.") }
            goNavigation(RegisterAppNavigation.NavigateToSuccessful)
            resetForm()
        }
    }

    private fun getAppRegistered(): RegisterApp{
        val currentState = uiState.value
        val appWhitOutRecommendation = RegisterApp(
            nameApp = currentState.nameApp,
            version = currentState.version,
            usersActive = currentState.usersActive,
            costs = currentState.costs,
            comments = currentState.comments,
            keyActivities = currentState.keyActivities,
            numberOfIncidents = currentState.numberOfIncidents,
            typeApp = currentState.typeApp,
            frequency = currentState.frequency,
            priority = currentState.priority,
            statusApp = currentState.statusApp,
        )
        val app = appWhitOutRecommendation.copy(
            recommendations = recommendDeleting(appWhitOutRecommendation).recommendation
        )
        return app
    }

    private fun resetForm(){
        setUiState { copy(
            nameApp = "",
            version = "",
            usersActive = "",
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