package com.pe.presentation.manager.register.ui

sealed class RegisterAppNavigation{
    object NavigateToBack : RegisterAppNavigation()

    object NavigateToSuccessful: RegisterAppNavigation()

}
