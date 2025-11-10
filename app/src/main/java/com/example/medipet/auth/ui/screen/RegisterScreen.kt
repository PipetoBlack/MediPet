package com.example.medipet.auth.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medipet.auth.ui.components.InputText

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Variables de error (puedes validar más adelante)
    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Create Account 🐾", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))

            // 👇 Aquí usas tu componente personalizado
            InputText(
                valor = name,
                error = nameError,
                label = "Full Name",
                onChange = { name = it }
            )
            Spacer(modifier = Modifier.height(12.dp))

            InputText(
                valor = email,
                error = emailError,
                label = "Email ID",
                onChange = { email = it }
            )
            Spacer(modifier = Modifier.height(12.dp))

            InputText(
                valor = password,
                error = passwordError,
                label = "Password",
                onChange = { password = it }
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Aquí puedes hacer validaciones simples
                    nameError = if (name.isBlank()) "Please enter your name" else null
                    emailError = if (email.isBlank()) "Email required" else null
                    passwordError = if (password.length < 6) "Min 6 characters" else null

                    if (nameError == null && emailError == null && passwordError == null) {
                        onRegisterSuccess()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign up")
            }

            TextButton(onClick = onNavigateToLogin) {
                Text("Already have an account? Log in")
            }
        }
    }
}
