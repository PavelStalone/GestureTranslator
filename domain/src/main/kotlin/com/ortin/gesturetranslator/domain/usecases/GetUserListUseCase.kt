package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.RoomDatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetUserListUseCase(private val roomDatabaseRepository: RoomDatabaseRepository) {
    suspend fun execute(): Flow<List<UserEntityDomain>> {
        return roomDatabaseRepository.getUserList()
    }
}
