package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateUserInformationUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(user: UserEntityDomain) {
        userRepository.updateUser(user)
    }
}
