package com.pe.presentation.manager.apps.ui.list

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pe.manager.domain.entity.RegisterApp
import com.pe.manager.domain.usescase.DeleteAppUseCase
import com.pe.manager.domain.usescase.GetAppsUseCase
import com.pe.presentation.manager.apps.ui.detail.DetailAppNavigation
import com.pe.presentation.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListAppsViewModel @Inject constructor(
    private val getAppsUseCase: GetAppsUseCase,
    private val deleteAppUseCase: DeleteAppUseCase,
    ) : BaseViewModel<ListAppsUiState, ListAppsIntent, ListAppsNavigation>() {

    override fun createInitialState(): ListAppsUiState {
        return ListAppsUiState()
    }

    override suspend fun handleIntent(intent: ListAppsIntent) {
        when (intent) {
            ListAppsIntent.OnNavigateToBack -> goNavigation(ListAppsNavigation.NavigateToBack)
            ListAppsIntent.ValidateRooted -> getApps()
            is ListAppsIntent.OnNavigateToDetailApp -> goNavigation(
                ListAppsNavigation.NavigateToDetailApp(
                    intent.appRegistered.idApp
                )
            )

            is ListAppsIntent.OnDeletApp -> deleteApp()
            ListAppsIntent.OnDismissedDialog -> setUiState { copy(showDialog = false) }
            is ListAppsIntent.OnShoweddDialog -> setUiState { copy(showDialog = true, appSelected = intent.appRegistered) }
            is ListAppsIntent.UpdateRecomendation -> updateRecommendation(intent.recomendation, intent.appRegistered)
            is ListAppsIntent.UpdateShowRecomendation -> updateShowRecommendation(intent.appRegistered)
        }
    }

    private fun getApps() {
        viewModelScope.launch {
            val result = getAppsUseCase()
            result?.let {
                setUiState { copy(listApps = it) }
            }

        }
    }

    private fun deleteApp() {
        setUiState { copy(showDialog = false) }
        viewModelScope.launch {
            val result = deleteAppUseCase(uiState.value.appSelected.idApp)
            val newArray = ArrayList(uiState.value.listApps.filter { it.idApp != uiState.value.appSelected.idApp })
            setUiState { copy(listApps = newArray) }
            goNavigation(ListAppsNavigation.NavigateToSuccessful)
        }
    }

    private fun updateShowRecommendation(appRegistered: RegisterApp){
        val updatedList  = uiState.value.listApps.map { app  ->
            if (app.idApp == appRegistered.idApp) {
                app.copy(showRecommendation = !app.showRecommendation)
            } else {
                app
            }
        }
        setUiState { copy(listApps = ArrayList(updatedList)) }
    }
    private fun updateRecommendation(recommendation: String, appRegistered: RegisterApp){
        val updatedList  = uiState.value.listApps.map { app  ->
            if (app.idApp == appRegistered.idApp) {
                app.copy(recommendations = recommendation)
            } else {
                app
            }
        }
        setUiState { copy(listApps = ArrayList(updatedList)) }
    }
}