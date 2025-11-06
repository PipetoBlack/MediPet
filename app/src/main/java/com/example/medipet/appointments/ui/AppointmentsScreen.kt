package com.example.medipet.appointments.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun AppointmentsScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Mis citas", style = MaterialTheme.typography.headlineSmall)
        // Aquí luego listaremos las reservas desde Room
    }
}