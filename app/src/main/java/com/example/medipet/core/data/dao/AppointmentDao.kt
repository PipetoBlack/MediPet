package com.example.medipet.core.data.dao

import androidx.room.*
import com.example.medipet.core.data.entities.Appointment
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM appointments ORDER BY date DESC")
    fun getAll(): Flow<List<Appointment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(appointment: Appointment)

    @Delete
    suspend fun delete(appointment: Appointment)
}