package com.example.medipet.core.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments")
data class Appointment(
    @PrimaryKey val id: String,
    val patientId: String,
    val specialistId: String,
    val date: Long,
    val status: String
)