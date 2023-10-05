package com.ortin.gesturetranslator.core.repository

import com.ortin.gesturetranslator.core.managers.model_coordinate.ModelCoordinateManager
import com.ortin.gesturetranslator.core.managers.model_coordinate.models.ModelCoordinateArray
import com.ortin.gesturetranslator.core.managers.model_coordinate.models.ModelCoordinateClassificationArray
import com.ortin.gesturetranslator.domain.models.CoordinateClassification
import com.ortin.gesturetranslator.domain.models.HandDetected
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository
import kotlin.math.abs

class RecognizeCoordinateRepositoryImpl(private val modelCoordinateManager: ModelCoordinateManager) :
    RecognizeCoordinateRepository {
    override fun recognise(handDetected: HandDetected): CoordinateClassification =
        mapToCoreCoordinateClassification(modelCoordinateManager.recognise(mapToCoreCoordinate(handDetected)))

    // Правила перевода для связи domain и core модулей
    private fun mapToCoreCoordinate(handDetected: HandDetected): ModelCoordinateArray {
        val oldCoord = handDetected.coordinates
        val coord = DoubleArray(oldCoord.size).mapIndexed { index, _ -> oldCoord[index].toDouble() }
            .toDoubleArray()

        var mainX = coord[0]
        var mainY = coord[1]
        var minX = 10.0
        var minY = 10.0

        for (i in coord.indices step 2) {
            coord[i] = coord[i] - mainX
            coord[i + 1] = coord[i + 1] - mainY
            coord[i + 1] = abs(coord[i + 1])
            minX = minX.coerceAtMost(coord[i])
            minY = minY.coerceAtMost(coord[i + 1])
        }

        val offsetX = if (minX < 0) abs(minX) else 0.0
        val offsetY = if (minY < 0) abs(minY) else 0.0
        val maxWidth = 1.0
        val maxHeight = 1.0
        var maxX = 0.0
        var maxY = 0.0

        for (i in coord.indices step 2){
            coord[i] += offsetX
            coord[i + 1] += offsetY
            maxX = maxX.coerceAtLeast(coord[i])
            maxY = maxY.coerceAtLeast(coord[i + 1])
        }

        val coefY = if (maxY != 0.0) abs(maxHeight / maxY) else 0.0
        val coefX = if (maxX != 0.0) abs(maxWidth / maxX) else 0.0

        for (i in coord.indices step 2){
            coord[i] *= coefX
            coord[i + 1] *= coefY
        }
        return ModelCoordinateArray(coord)
    }

    private fun mapToCoreCoordinateClassification(modelCoordinateClassificationArray: ModelCoordinateClassificationArray) : CoordinateClassification =
        CoordinateClassification(modelCoordinateClassificationArray.label, modelCoordinateClassificationArray.percent)
}
