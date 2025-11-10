package com.example.medipet.auth.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUIState(
    val username: String = "",
    val password: String = "",
    val error: String? = null,
    val isLoggedIn: Boolean = false
)

class AuthViewModel : ViewModel() {

    private val _state = MutableStateFlow(LoginUIState())
    val state: StateFlow<LoginUIState> = _state.asStateFlow()

    fun onUsernameChange(v: String) = _state.update { it.copy(username = v, error = null) }

    fun onPasswordChange(v: String) = _state.update { it.copy(password = v, error = null) }

    fun login(onSuccess: () -> Unit) {
        val current = _state.value
        if (current.username == "admin" && current.password == "admin") {
            _state.update { it.copy(isLoggedIn = true, error = null) }
            onSuccess()
        } else {
            _state.update { it.copy(error = "Credenciales inválidas") }
        }
    }

    fun logout() {
        _state.update { LoginUIState() } // resetea todo
    }
}
