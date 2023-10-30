package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.UserRoomDatabaseRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRoomDatabaseRepository: UserRoomDatabaseRepository
) {
    suspend fun execute(user: UserEntityDomain) {
        userRoomDatabaseRepository.deleteUser(user)
    }
}
