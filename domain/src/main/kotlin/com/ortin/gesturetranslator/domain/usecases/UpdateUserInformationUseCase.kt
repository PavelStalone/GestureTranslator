package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.UserRoomDatabaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateUserInformationUseCase @Inject constructor(
    private val userRoomDatabaseRepository: UserRoomDatabaseRepository
) {
    suspend fun execute(user: UserEntityDomain) {
        userRoomDatabaseRepository.updateUserInformation(user)
    }
}
