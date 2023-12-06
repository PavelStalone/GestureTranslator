package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.entities.UserEntityDomain
import com.ortin.gesturetranslator.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(): Flow<List<UserEntityDomain>> {
        return userRepository.getUserList()
    }
}
