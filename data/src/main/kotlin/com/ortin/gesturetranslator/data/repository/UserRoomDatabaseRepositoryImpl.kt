package com.ortin.gesturetranslator.data.repository

import com.ortin.gesturetranslator.data.db.UserDao
import com.ortin.gesturetranslator.data.entities.UserEntity
import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.UserRoomDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRoomDatabaseRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
) : UserRoomDatabaseRepository {
    override suspend fun addUser(user: UserEntityDomain) {
        userDao.insertUser(user.mapToData())
    }

    override suspend fun deleteUser(user: UserEntityDomain) {
        userDao.deleteUser(user.mapToData())
    }

    override suspend fun updateUserInformation(user: UserEntityDomain) {
        userDao.updateUser(user.mapToData())
    }

    override suspend fun getUserList(): Flow<List<UserEntityDomain>> {
        return dataFlowMapToDomain(userDao.getAllUsers())
    }

    override suspend fun getUser(login: String): UserEntityDomain {
        return userDao.getUser(login).mapToDomain()
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
