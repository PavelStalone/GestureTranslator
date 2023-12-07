package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.di.Dispatcher
import com.ortin.gesturetranslator.domain.di.GtDispatchers
import com.ortin.gesturetranslator.domain.models.CorrectedTextModel
import com.ortin.gesturetranslator.domain.models.NetworkResponse
import com.ortin.gesturetranslator.domain.models.RecognizedTextModel
import com.ortin.gesturetranslator.domain.repository.AutocorrectTextRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CorrectTextUseCase @Inject constructor(
    private val autocorrectTextRepository: AutocorrectTextRepository,
    @Dispatcher(GtDispatchers.IO) private val dispatcher: CoroutineDispatcher
) {
    suspend fun invoke(data: RecognizedTextModel) = withContext(dispatcher) {
        return@withContext autocorrectTextRepository.correctText(data)
    }
}
