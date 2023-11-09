package com.ortin.gesturetranslator.core.repository

import com.ortin.gesturetranslator.core.managers.model_coordinate.ModelCoordinateManager
import com.ortin.gesturetranslator.core.managers.model_coordinate.models.ModelCoordinateArray
import com.ortin.gesturetranslator.core.managers.model_coordinate.models.ModelCoordinateClassificationArray
import com.ortin.gesturetranslator.domain.models.CoordinateClassification
import com.ortin.gesturetranslator.domain.models.ImageDetected
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs

@Singleton
class RecognizeCoordinateRepositoryImpl @Inject constructor(private val modelCoordinateManager: ModelCoordinateManager) :
    RecognizeCoordinateRepository {
    override fun recognise(imageDetected: ImageDetected): CoordinateClassification =
        mapToCoreCoordinateClassification(
            modelCoordinateManager.recognise(
                mapToCoreCoordinate(
                    imageDetected
                )
            )
        )

    // Правила перевода для связи domain и core модулей
    private fun mapToCoreCoordinate(imageDetected: ImageDetected): ModelCoordinateArray {
        val oldCoord = imageDetected.coordinates
        val coord = List(oldCoord.size) { index -> oldCoord[index].toDouble() }.toMutableList()

        val mainX = coord[0]
        val mainY = coord[1]
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

        for (i in coord.indices step 2) {
            coord[i] += offsetX
            coord[i + 1] += offsetY
            maxX = maxX.coerceAtLeast(coord[i])
            maxY = maxY.coerceAtLeast(coord[i + 1])
        }

        val coefY = if (maxY != 0.0) abs(maxHeight / maxY) else 0.0
        val coefX = if (maxX != 0.0) abs(maxWidth / maxX) else 0.0

        for (i in coord.indices step 2) {
            coord[i] *= coefX
            coord[i + 1] *= coefY
        }

        return ModelCoordinateArray(coord)
    }

    private fun mapToCoreCoordinateClassification(modelCoordinateClassificationArray: ModelCoordinateClassificationArray): CoordinateClassification =
        CoordinateClassification(
            modelCoordinateClassificationArray.label,
            modelCoordinateClassificationArray.percent
        )
}
