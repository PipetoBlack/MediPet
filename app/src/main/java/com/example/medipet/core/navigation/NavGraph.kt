package com.example.medipet.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.medipet.auth.ui.screen.LoginScreen
import com.example.medipet.auth.ui.screen.RegisterScreen
import com.example.medipet.auth.ui.WelcomeScreen
import com.example.medipet.appointments.ui.AgendaScreen
import com.example.medipet.appointments.ui.AppointmentsScreen
import com.example.medipet.users.ui.UserProfileScreen
import com.example.medipet.specialists.ui.SpecialistProfileScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// ✅ Definición de rutas centralizadas
object Routes {
    const val Welcome = "welcome"
    const val Login = "login"
    const val Register = "register"
    const val Appointments = "appointments"
    const val Agenda = "agenda"
    const val SpecialistProfile = "specialistProfile"
    const val UserProfile = "userProfile"
}

// ✅ Composable principal con navegación y drawer
@Composable
fun MediPetNav() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = Routes.Welcome) {

        // --- Pantalla de bienvenida ---
        composable(Routes.Welcome) {
            WelcomeScreen(
                onNavigateToLogin = { navController.navigate(Routes.Login) },
                onNavigateToRegister = { navController.navigate(Routes.Register) }
            )
        }

        // --- Login ---
        composable(Routes.Login) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.Appointments) {
                        popUpTo(Routes.Login) { inclusive = true } // Limpia el backstack
                    }
                },
                onNavigateToRegister = { navController.navigate(Routes.Register) },
                onNavigateToWelcome = { navController.navigate(Routes.Welcome) }
            )
        }

        // --- Registro ---
        composable(Routes.Register) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Register) { inclusive = true }
                    }
                },
                onNavigateToLogin = { navController.navigate(Routes.Login) }
            )
        }

        // --- Shell con menú hamburguesa (Drawer) ---
        navigation(startDestination = Routes.Appointments, route = "main_shell") {

            composable(Routes.Appointments) {
                DrawerScaffold(
                    currentRoute = Routes.Appointments,
                    onNavigate = { navController.navigate(it) },
                    drawerState = drawerState,
                    scope = scope
                ) {
                    AppointmentsScreen(navController)
                }
            }

            composable(Routes.Agenda) {
                DrawerScaffold(
                    currentRoute = Routes.Agenda,
                    onNavigate = { navController.navigate(it) },
                    drawerState = drawerState,
                    scope = scope
                ) {
                    AgendaScreen(navController)
                }
            }

            composable(Routes.SpecialistProfile) {
                DrawerScaffold(
                    currentRoute = Routes.SpecialistProfile,
                    onNavigate = { navController.navigate(it) },
                    drawerState = drawerState,
                    scope = scope
                ) {
                    SpecialistProfileScreen()
                }
            }

            composable(Routes.UserProfile) {
                DrawerScaffold(
                    currentRoute = Routes.UserProfile,
                    onNavigate = { navController.navigate(it) },
                    drawerState = drawerState,
                    scope = scope
                ) {
                    UserProfileScreen()
                }
            }
        }
    }
}

// ✅ Drawer + Scaffold con el icono de hamburguesa
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerScaffold(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    drawerState: DrawerState,
    scope: CoroutineScope,
    content: @Composable () -> Unit
) {
    val destinations = listOf(
        DrawerItem("Citas", Routes.Appointments),
        DrawerItem("Agenda", Routes.Agenda),
        DrawerItem("Veterinarios", Routes.SpecialistProfile),
        DrawerItem("Mi Perfil", Routes.UserProfile)
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Medipet",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
                destinations.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(item.label) },
                        selected = currentRoute == item.route,
                        onClick = {
                            scope.launch { drawerState.close() }
                            if (currentRoute != item.route) {
                                onNavigate(item.route)
                            }
                        },
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text(appBarTitle(currentRoute)) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { padding ->
            Surface(Modifier.padding(padding)) {
                content()
            }
        }
    }
}

// ✅ Data class para el menú lateral
private data class DrawerItem(val label: String, val route: String)

// ✅ Título dinámico en AppBar
@Composable
private fun appBarTitle(route: String?): String = when (route) {
    Routes.Appointments -> "Citas"
    Routes.Agenda -> "Agenda"
    Routes.SpecialistProfile -> "Veterinarios"
    Routes.UserProfile -> "Mi Perfil"
    else -> ""
}
