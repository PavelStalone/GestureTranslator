package com.ortin.gesturetranslator.network.repository

import com.ortin.gesturetranslator.domain.di.Dispatcher
import com.ortin.gesturetranslator.domain.di.GtDispatchers
import com.ortin.gesturetranslator.domain.models.RecognizedTextModel
import com.ortin.gesturetranslator.domain.repository.AutocorrectTextRepository
import com.ortin.gesturetranslator.network.AutoCorrectDataSourceImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AutocorrectTextRepositoryImpl @Inject constructor(
    @Dispatcher(GtDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val autoCorrectDataSourceImpl: AutoCorrectDataSourceImpl
) : AutocorrectTextRepository {

    override suspend fun correctText(recognizedTextModel: RecognizedTextModel) =
        withContext(dispatcher) {
            return@withContext autoCorrectDataSourceImpl.correctText(recognizedTextModel)
        }
}
