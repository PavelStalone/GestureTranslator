package com.ortin.gesturetranslator.data.repository

import com.ortin.gesturetranslator.data.db.UserRoomDB
import com.ortin.gesturetranslator.data.entities.UserEntity
import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.UserRoomDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRoomDatabaseRepositoryImpl @Inject constructor(
    private val userDatabase: UserRoomDB,
) : UserRoomDatabaseRepository {
    override suspend fun addUser(user: UserEntityDomain) {
        userDatabase.userDao.insertUser(user.mapToData())
    }

    override suspend fun deleteUser(user: UserEntityDomain) {
        userDatabase.userDao.deleteUser(user.mapToData())
    }

    override suspend fun updateUserInformation(user: UserEntityDomain) {
        userDatabase.userDao.updateUser(user.mapToData())
    }

    override suspend fun getUserList(): Flow<List<UserEntityDomain>> {
        return dataFlowMapToDomain(userDatabase.userDao.getAllUsers())
    }

    override suspend fun getUser(login: String): UserEntityDomain {
        return userDatabase.userDao.getUser(login).mapToDomain()
    }

    private fun UserEntityDomain.mapToData(): UserEntity =
        UserEntity(
            login = this.login,
            password = this.password,
            isSigned = this.isSigned
        )

    private fun UserEntity.mapToDomain(): UserEntityDomain =
        UserEntityDomain(
            login = this.login,
            password = this.password,
            isSigned = this.isSigned
        )

    private fun dataFlowMapToDomain(flow: Flow<List<UserEntity>>): Flow<List<UserEntityDomain>> =
        flow.map { list -> list.map { it.mapToDomain() } }
}
