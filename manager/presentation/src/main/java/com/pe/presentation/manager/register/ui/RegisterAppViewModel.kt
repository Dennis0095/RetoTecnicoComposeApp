package com.pe.presentation.manager.register.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pe.manager.domain.usescase.RegisterAppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterAppViewModel @Inject constructor(
    private val registerAppUseCase: RegisterAppUseCase
): ViewModel() {

    private val _screenState =
        MutableStateFlow(RegisterAppState())
    val screenState: StateFlow<RegisterAppState> get() = _screenState

    init {
        registerApp()
    }

    fun registerApp(){
        viewModelScope.launch {
            val result = registerAppUseCase()
            Log.d("DMA_LECTOR", " resultado = " + result)

            _screenState.value = screenState.value.copy(

            )
        }
    }
}