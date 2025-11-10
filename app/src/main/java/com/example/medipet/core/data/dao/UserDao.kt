package com.example.medipet.core.data.dao

import androidx.room.*
import com.example.medipet.core.data.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity) // ❌ antes usabas User (no existe esa clase)

    @Update
    suspend fun update(user: UserEntity) // ❌ vararg innecesario aquí, mejor uno

    @Query("SELECT * FROM usuarios WHERE correo = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): UserEntity?

    @Query("SELECT * FROM usuarios WHERE correo = :email LIMIT 1")
    suspend fun findByEmail(email: String): UserEntity?
}
