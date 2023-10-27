package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.RoomDatabaseRepository

class AddUserUseCase(private val roomDatabaseRepository: RoomDatabaseRepository) {
    suspend fun execute(user: UserEntityDomain) {
        roomDatabaseRepository.addUser(user)
    }
}
