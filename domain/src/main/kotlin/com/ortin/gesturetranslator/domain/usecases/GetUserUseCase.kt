package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.UserRoomDatabaseRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRoomDatabaseRepository: UserRoomDatabaseRepository
) {
    suspend fun execute(login: String): UserEntityDomain {
        return userRoomDatabaseRepository.getUser(login)
    }
}
