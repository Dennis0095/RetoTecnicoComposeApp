package com.pe.presentation.manager.apps.ui.detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pe.manager.domain.usescase.DeleteAppUseCase
import com.pe.manager.domain.usescase.GetAppUseCase
import com.pe.presentation.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailAppViewModel @Inject constructor(
    private val getAppUseCase: GetAppUseCase,
    private val deleteAppUseCase: DeleteAppUseCase,
) : BaseViewModel<DetailAppUiState, DetailAppIntent, DetailAppNavigation>() {

    override fun createInitialState(): DetailAppUiState {
        return DetailAppUiState()
    }

    override suspend fun handleIntent(intent: DetailAppIntent) {
        when (intent) {
            DetailAppIntent.OnNavigateToBack -> goNavigation(DetailAppNavigation.NavigateToBack)
            is DetailAppIntent.ValidateRooted -> setData(intent.id)
            DetailAppIntent.OnDeleteApp -> deleteApp()
            DetailAppIntent.OnDismissedDialog -> setUiState { copy(showDialog = false) }
            DetailAppIntent.OnNavigationEditApp -> goNavigation(
                DetailAppNavigation.NavigateToEditApp(
                    uiState.value.appRegistered.idApp
                )
            )

            DetailAppIntent.OnShoweddDialog -> setUiState { copy(showDialog = true) }
        }
    }

    private fun setData(idApp: Int) {
        viewModelScope.launch {
            val result = getAppUseCase(idApp)
            result?.let {
                setUiState { copy(appRegistered = it) }
            }

        }
    }

    private fun deleteApp() {
        setUiState { copy(showDialog = false, showLoadingButton = true) }
        viewModelScope.launch {
            val result = deleteAppUseCase(uiState.value.appRegistered.idApp)
            setUiState { copy(showLoadingButton = false) }
            goNavigation(DetailAppNavigation.NavigateToSuccessful)
        }
    }
}