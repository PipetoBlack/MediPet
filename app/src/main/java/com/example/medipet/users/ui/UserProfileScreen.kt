package com.example.medipet.users.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UserProfileScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Perfil de usuario", style = MaterialTheme.typography.headlineSmall)
    }
}