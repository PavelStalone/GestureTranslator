package com.ortin.gesturetranslator.domain.repository

import com.ortin.gesturetranslator.domain.models.CorrectedTextModel
import com.ortin.gesturetranslator.domain.models.NetworkResponse
import com.ortin.gesturetranslator.domain.models.RecognizedTextModel

interface AutocorrectTextRepository {
    suspend fun correctText(recognizedTextModel: RecognizedTextModel): NetworkResponse<CorrectedTextModel>
}
