package com.ortin.gesturetranslator.core.managers.model_coordinate

import com.ortin.gesturetranslator.core.managers.model_coordinate.ml_models.MlModel
import com.ortin.gesturetranslator.core.managers.model_coordinate.models.ModelCoordinateArray
import com.ortin.gesturetranslator.core.managers.model_coordinate.models.ModelCoordinateClassificationArray

class ModelCoordinateManagerImpl : ModelCoordinateManager {
    private val LABELS = listOf(
        "А",
        "Б",
        "В",
        "Г",
        "Д",
        "Е",
        "Ж",
        "З",
        "И",
        "К",
        "Л",
        "М",
        "Н",
        "О",
        "П",
        "Р",
        "С",
        "Т",
        "У",
        "Ф",
        "Х",
        "Ц",
        "Ч",
        "Ш",
        "Ы",
        "Ь",
        "Э",
        "Ю",
        "Я"
    )

    override fun recognise(modelCoordinateArray: ModelCoordinateArray): ModelCoordinateClassificationArray {
        val results: List<Double> = MlModel.score(modelCoordinateArray.coordinate)
        var maxScore = 0.0
        var maxIndex = 0

        results.forEachIndexed { i, _ ->
            if (results[i] > maxScore) {
                maxScore = results[i]
                maxIndex = i
            }
        }
        return ModelCoordinateClassificationArray((maxScore * 100).toFloat(), LABELS[maxIndex])
    }
}
