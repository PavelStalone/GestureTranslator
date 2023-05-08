package com.ortin.gesturetranslator.core.repository;

import com.ortin.gesturetranslator.core.managers.model_coordinate.ModelCoordinateManager;
import com.ortin.gesturetranslator.core.managers.model_coordinate.models.ModelCoordinateArray;
import com.ortin.gesturetranslator.core.managers.model_coordinate.models.ModelCoordinateClassificationArray;
import com.ortin.gesturetranslator.domain.models.CoordinateClassification;
import com.ortin.gesturetranslator.domain.models.HandDetected;
import com.ortin.gesturetranslator.domain.repository.RecognizeCoordinateRepository;

public class RecognizeCoordinateRepositoryImpl implements RecognizeCoordinateRepository {
    private ModelCoordinateManager modelCoordinateManager;

    public RecognizeCoordinateRepositoryImpl(ModelCoordinateManager modelCoordinateManager) {
        this.modelCoordinateManager = modelCoordinateManager;
    }

    @Override
    public CoordinateClassification recognise(HandDetected handDetected) {
        return mapToCoreCoordinateClassification(modelCoordinateManager.recognise(mapToCoreCoordinate(handDetected)));
    }



    // Правила перевода для связи domain и core модулей

    private ModelCoordinateArray mapToCoreCoordinate(HandDetected handDetected) {
        float[] oldCoord = handDetected.getCoordinates();
        double[] coord = new double[oldCoord.length];

        for (int i = 0; i < oldCoord.length; i++) {
            coord[i] = (double) oldCoord[i];
        }

        return new ModelCoordinateArray(coord);
    }

    private CoordinateClassification mapToCoreCoordinateClassification(ModelCoordinateClassificationArray modelCoordinateClassificationArray) {
        return new CoordinateClassification(modelCoordinateClassificationArray.getLabel(), modelCoordinateClassificationArray.getPercent());
    }
}
