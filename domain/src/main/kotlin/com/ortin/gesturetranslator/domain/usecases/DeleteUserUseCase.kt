package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.RoomDatabaseRepository

class DeleteUserUseCase(private val roomDatabaseRepository: RoomDatabaseRepository) {
    suspend fun execute(user: UserEntityDomain) {
        roomDatabaseRepository.deleteUser(user)
    }
}
