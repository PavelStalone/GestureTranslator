package com.ortin.gesturetranslator.core.managers.model_coordinate;

import android.util.Log;

import com.ortin.gesturetranslator.core.managers.model_coordinate.ml_models.MlModel;
import com.ortin.gesturetranslator.core.managers.model_coordinate.models.ModelCoordinateArray;
import com.ortin.gesturetranslator.core.managers.model_coordinate.models.ModelCoordinateClassificationArray;

import java.util.Arrays;

public class ModelCoordinateManagerManagerImpl implements ModelCoordinateManager {

    public static final String[] LABELS = new String[]{"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Ь", "Ы", "Э", "Ю", "Я"};

    @Override
    public ModelCoordinateClassificationArray recognise(ModelCoordinateArray modelCoordinateArray) {
        Log.e("recognise", "recognise: " + Arrays.toString(modelCoordinateArray.getCoordinate()));
        double[] results = MlModel.score(modelCoordinateArray.getCoordinate());
        double maxScore = 0.0;
        int maxIndex = 0;

        Log.e("recognise", "results: " + Arrays.toString(results));

        for (int i = 0; i < results.length; i++) {
            if (results[i] > maxScore) {
                maxScore = results[i];
                maxIndex = i;
            }
        }

        return new ModelCoordinateClassificationArray((float) maxScore * 100, LABELS[maxIndex]);
    }
}
