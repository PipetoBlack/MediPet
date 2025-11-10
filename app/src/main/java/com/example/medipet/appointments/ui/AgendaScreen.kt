// appointments/AgendaScreen.kt --mejorada
package com.example.medipet.appointments.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medipet.core.navigation.Routes

/**
 * AgendaScreen recibe una función onNavigate en vez de recibir NavController directamente.
 * Esto desacopla la UI del sistema de navegación y facilita pruebas y reutilización.
 *
 * onNavigate: función que se llamará con la ruta a la que se quiere navegar.
 */
@Composable
fun AgendaScreen(
    onNavigate: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Agenda de citas",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // ejemplo: navegar a la pantalla de creación de citas (ajusta la ruta si la tienes)
                // Si no tienes una ruta específica, cámbiala por la que corresponda en Routes
                onNavigate(Routes.Appointments) // o la ruta que quieras usar
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agendar nueva cita")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Ejemplo de botón para ir al perfil de usuario
        OutlinedButton(
            onClick = { onNavigate(Routes.UserProfile) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver mi perfil")
        }
    }
}
