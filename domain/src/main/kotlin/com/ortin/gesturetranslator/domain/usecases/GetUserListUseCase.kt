package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.UserRoomDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserListUseCase @Inject constructor(
    private val userRoomDatabaseRepository: UserRoomDatabaseRepository
) {
    suspend fun execute(): Flow<List<UserEntityDomain>> {
        return userRoomDatabaseRepository.getUserList()
    }
}
