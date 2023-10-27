package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.UserRoomDatabaseRepository

class GetUserUseCase(private val userRoomDatabaseRepository: UserRoomDatabaseRepository) {
    suspend fun execute(login: String): UserEntityDomain {
        return userRoomDatabaseRepository.getUser(login)
    }
}
