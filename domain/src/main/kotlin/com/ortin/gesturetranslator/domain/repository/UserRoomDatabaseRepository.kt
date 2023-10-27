package com.ortin.gesturetranslator.domain.repository

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import kotlinx.coroutines.flow.Flow

interface UserRoomDatabaseRepository {
    suspend fun addUser(user: UserEntityDomain)
    suspend fun deleteUser(user: UserEntityDomain)
    suspend fun updateUserInformation(user: UserEntityDomain)
    suspend fun getUserList(): Flow<List<UserEntityDomain>>
    suspend fun getUser(login: String): UserEntityDomain
}
