package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.UserRoomDatabaseRepository

class UpdateUserInformationUseCase(private val userRoomDatabaseRepository: UserRoomDatabaseRepository) {
    suspend fun execute(user: UserEntityDomain) {
        userRoomDatabaseRepository.updateUserInformation(user)
    }
}
