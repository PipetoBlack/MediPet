package com.example.medipet.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medipet.core.data.entities.UserEntity
import com.example.medipet.core.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// --- Representa el estado de la UI del formulario de registro ---
data class RegisterUIState(
    val nombre: String = "",
    val correo: String = "",
    val password: String = "",
    val confirmarPassword: String = "",
    val error: String? = null,
    val registroExitoso: Boolean = false
)

// --- ViewModel que maneja la lógica de registro ---
class RegisterViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    // Estado observable de la interfaz
    private val _uiState = MutableStateFlow(RegisterUIState())
    val uiState: StateFlow<RegisterUIState> = _uiState.asStateFlow()

    // Actualiza los campos del formulario
    fun onNombreChange(value: String) = _uiState.update { it.copy(nombre = value, error = null) }
    fun onCorreoChange(value: String) = _uiState.update { it.copy(correo = value, error = null) }
    fun onPasswordChange(value: String) = _uiState.update { it.copy(password = value, error = null) }
    fun onConfirmarPasswordChange(value: String) =
        _uiState.update { it.copy(confirmarPassword = value, error = null) }

    // --- Lógica principal de registro ---
    fun registrarUsuario(onSuccess: () -> Unit) {
        val state = _uiState.value

        // Validaciones básicas
        when {
            state.nombre.isBlank() || state.correo.isBlank() || state.password.isBlank() -> {
                _uiState.update { it.copy(error = "Todos los campos son obligatorios") }
                return
            }
            state.password != state.confirmarPassword -> {
                _uiState.update { it.copy(error = "Las contraseñas no coinciden") }
                return
            }
        }

        // Ejecución asíncrona (Room no permite operaciones en el hilo principal)
        viewModelScope.launch {
            val correoExistente = userRepository.isEmailTaken(state.correo)
            if (correoExistente) {
                _uiState.update { it.copy(error = "El correo ya está registrado") }
            } else {
                val nuevoUsuario = UserEntity(
                    nombre = state.nombre,
                    correo = state.correo,
                    password = state.password
                )
                userRepository.registerUser(nuevoUsuario)
                _uiState.update { it.copy(registroExitoso = true, error = null) }
                onSuccess()
            }
        }
    }
}
