// appointments/AppointmentScreen.kt --mejorada
package com.example.medipet.appointments.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medipet.core.navigation.Routes

/**
 * Pantalla de "Mis citas".
 *
 * onNavigate: lambda que se usa para indicar a la capa de navegación
 * a qué ruta debe ir el usuario (sin depender directamente de NavController).
 */
@Composable
fun AppointmentsScreen(
    onNavigate: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mis citas",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de ejemplo para crear una nueva cita
        Button(
            onClick = {
                // Emitimos el evento de navegación
                onNavigate(Routes.Agenda)  // 👈 usa la ruta definida en tu NavGraph
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agendar nueva cita")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Ejemplo de botón para ir al perfil del usuario
        OutlinedButton(
            onClick = { onNavigate(Routes.UserProfile) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a mi perfil")
        }
    }
}
