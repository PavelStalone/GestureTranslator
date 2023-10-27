package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.RoomDatabaseRepository

class GetUserUseCase(private val roomDatabaseRepository: RoomDatabaseRepository) {
    suspend fun execute(login: String): UserEntityDomain {
        return roomDatabaseRepository.getUser(login)
    }
}
