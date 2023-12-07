package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.models.CorrectedTextModel
import com.ortin.gesturetranslator.domain.models.NetworkResponse
import com.ortin.gesturetranslator.domain.models.RecognizedTextModel
import com.ortin.gesturetranslator.domain.repository.AutocorrectTextRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CorrectTextUseCase @Inject constructor(
    private val autocorrectTextRepository: AutocorrectTextRepository
) : UseCase<RecognizedTextModel, NetworkResponse<CorrectedTextModel>> {

    override operator fun invoke(data: RecognizedTextModel) :NetworkResponse<CorrectedTextModel>  = runBlocking {
        withContext(Dispatchers.IO) {
            return@withContext autocorrectTextRepository.correctText(data)
        }
    }
}
