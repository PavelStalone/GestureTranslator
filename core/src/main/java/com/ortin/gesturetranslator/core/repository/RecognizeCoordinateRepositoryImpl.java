package com.ortin.gesturetranslator.core.repository;

import android.util.Log;

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

        double mainX = coord[0];
        double mainY = coord[1];

        double minX = 10, minY = 10;

        for (int i = 0; i < coord.length; i += 2) {
            coord[i] = coord[i] - mainX;
            coord[i + 1] = coord[i + 1] - mainY;
            coord[i + 1] = Math.abs(coord[i + 1]);
            minX = Math.min(minX, coord[i]);
            minY = Math.min(minY, coord[i + 1]);
        }

        double offsetX = minX < 0 ? Math.abs(minX) : 0;
        double offsetY = minY < 0 ? Math.abs(minY) : 0;

        double maxWidth = 1;
        double maxHeight = 1;

        double maxX = 0, maxY = 0;

        for (int i = 0; i < coord.length; i += 2) {
            coord[i] += offsetX;
            coord[i + 1] += offsetY;
            maxX = Math.max(maxX, coord[i]);
            maxY = Math.max(maxY, coord[i + 1]);
        }

        double coefY = maxY != 0 ? Math.abs(maxHeight / maxY) : 0;
        double coefX = maxX != 0 ? Math.abs(maxWidth / maxX) : 0;

        for (int i = 0; i < coord.length; i += 2) {
            coord[i] *= coefX;
            coord[i + 1] *= coefY;
        }

        return new ModelCoordinateArray(coord);
    }

    private CoordinateClassification mapToCoreCoordinateClassification(ModelCoordinateClassificationArray modelCoordinateClassificationArray) {
        return new CoordinateClassification(modelCoordinateClassificationArray.getLabel(), modelCoordinateClassificationArray.getPercent());
    }
}
