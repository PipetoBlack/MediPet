package com.example.medipet.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medipet.core.data.dao.AppointmentDao
import com.example.medipet.core.data.entities.Appointment

@Database(entities = [Appointment::class], version = 1)
abstract class MediPetDatabase : RoomDatabase() {
    abstract fun appointmentDao(): AppointmentDao
}