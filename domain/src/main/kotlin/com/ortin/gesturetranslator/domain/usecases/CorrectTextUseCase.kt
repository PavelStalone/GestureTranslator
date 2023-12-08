package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.models.RecognizedTextModel
import com.ortin.gesturetranslator.domain.repository.AutocorrectTextRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CorrectTextUseCase @Inject constructor(
    private val autocorrectTextRepository: AutocorrectTextRepository,
) {
    suspend operator fun invoke(data: RecognizedTextModel) = autocorrectTextRepository.correctText(data)
}
