package com.example.medipet.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medipet.auth.ui.LoginScreen
import com.example.medipet.appointments.ui.AgendaScreen
import com.example.medipet.appointments.ui.AppointmentsScreen
import com.example.medipet.users.ui.UserProfileScreen
import com.example.medipet.specialists.ui.SpecialistProfileScreen

@Composable
fun MediPetNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(onLoginSuccess = { navController.navigate("home") }) }
        composable("home") { AppointmentsScreen(navController) }
        composable("agenda") { AgendaScreen(navController) }
        composable("appointments") { AppointmentsScreen(navController) }
        composable("userProfile") { UserProfileScreen() }
        composable("specialistProfile") { SpecialistProfileScreen() }
    }
}