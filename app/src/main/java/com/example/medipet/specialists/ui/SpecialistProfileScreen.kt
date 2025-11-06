package com.example.medipet.specialists.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SpecialistProfileScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Perfil del especialista", style = MaterialTheme.typography.headlineSmall)
    }
}