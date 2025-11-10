package com.example.medipet.auth.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medipet.auth.viewmodel.AuthViewModel
import com.example.medipet.auth.ui.components.InputText

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToWelcome: () -> Unit
) {
    val uiState by viewModel.state.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome Back 👋", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))

            // ✅ Campo de usuario
            InputText(
                valor = uiState.username,
                error = uiState.error,
                label = "Email ID",
                onChange = viewModel::onUsernameChange
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ✅ Campo de contraseña
            InputText(
                valor = uiState.password,
                error = null,
                label = "Password",
                onChange = viewModel::onPasswordChange
            )

            Spacer(modifier = Modifier.height(8.dp))

            uiState.error?.let {
                Text(it, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(
                onClick = { viewModel.login(onLoginSuccess) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Log in")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = onNavigateToRegister) {
                Text("New to MediPet? Register")
            }

            TextButton(onClick = onNavigateToWelcome) {
                Text("← Back")
            }
        }
    }
}
