package com.example.medipet.core.data.repository

import com.example.medipet.core.data.dao.UserDao
import com.example.medipet.core.data.entities.UserEntity

class UserRepository(private val userDao: UserDao) {

    suspend fun registerUser(user: UserEntity) = userDao.insert(user)

    suspend fun loginUser(email: String, password: String): UserEntity? =
        userDao.login(email, password)

    suspend fun isEmailTaken(email: String): Boolean =
        userDao.findByEmail(email) != null
}
