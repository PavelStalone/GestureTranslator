package com.ortin.gesturetranslator.domain.repository

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addUser(user: UserEntityDomain)
    suspend fun deleteUser(user: UserEntityDomain)
    suspend fun updateUser(user: UserEntityDomain)
    suspend fun getUserList(): Flow<List<UserEntityDomain>>
    suspend fun getUserByLogin(login: String): UserEntityDomain
}
