package com.example.medipet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.medipet.core.navigation.MediPetNav
import com.example.medipet.ui.theme.MediPetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediPetTheme {
                MediPetNav() // Montamos la navegación
            }
        }
    }
}