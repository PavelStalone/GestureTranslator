package com.ortin.gesturetranslator.core.managers.model_coordinate

import com.ortin.gesturetranslator.core.managers.model_coordinate.models.ModelCoordinateArray
import com.ortin.gesturetranslator.core.managers.model_coordinate.models.ModelCoordinateClassificationArray

interface ModelCoordinateManager {
    fun recognise(modelCoordinateArray: ModelCoordinateArray): ModelCoordinateClassificationArray
}
