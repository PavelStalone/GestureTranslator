package com.ortin.gesturetranslator.data.repository

import com.ortin.gesturetranslator.data.db.RoomDB
import com.ortin.gesturetranslator.data.entities.UserEntity
import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.RoomDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomDatabaseRepositoryImpl(
    private val database: RoomDB,
) : RoomDatabaseRepository {
    override suspend fun addUser(user: UserEntityDomain) {
        database.dao.insertUser(user.mapToData())
    }

    override suspend fun deleteUser(user: UserEntityDomain) {
        database.dao.deleteUser(user.mapToData())
    }

    override suspend fun updateUserInformation(user: UserEntityDomain) {
        database.dao.updateUser(user.mapToData())
    }

    override suspend fun getUserList(): Flow<List<UserEntityDomain>> {
        return dataFlowMapToDomain(database.dao.getAllUsers())
    }


    override suspend fun getUser(login: String): UserEntityDomain {
        return database.dao.getUser(login).mapToDomain()
    }


    private fun UserEntityDomain.mapToData(): UserEntity =
        UserEntity(
            id = this.id,
            login = this.login,
            password = this.password,
            isSigned = this.isSigned
        )

    private fun UserEntity.mapToDomain(): UserEntityDomain =
        UserEntityDomain(
            id = this.id,
            login = this.login,
            password = this.password,
            isSigned = this.isSigned
        )

    private fun dataFlowMapToDomain(flow: Flow<List<UserEntity>>): Flow<List<UserEntityDomain>> =
        flow.map { list -> list.map { it.mapToDomain() } }
}
