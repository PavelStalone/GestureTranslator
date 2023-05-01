package com.example.gesturetranslator.core.repository;

import com.example.gesturetranslator.core.managers.mediapipe_manager.MediaPipeManager;
import com.example.gesturetranslator.core.managers.mediapipe_manager.listeners.MPDetectionListener;
import com.example.gesturetranslator.core.managers.mediapipe_manager.models.MPDetection;
import com.example.gesturetranslator.core.managers.mediapipe_manager.models.MPImageInput;
import com.example.gesturetranslator.domain.listeners.DetectionHandListener;
import com.example.gesturetranslator.domain.models.HandDetected;
import com.example.gesturetranslator.domain.models.Image;
import com.example.gesturetranslator.domain.repository.HandDetectionRepository;
import com.google.mediapipe.framework.image.BitmapImageBuilder;
import com.google.mediapipe.framework.image.MPImage;
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark;
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult;

import java.util.Iterator;

public class HandDetectionRepositoryImpl implements HandDetectionRepository {
    private MediaPipeManager mediaPipeManager;

    public HandDetectionRepositoryImpl(MediaPipeManager mediaPipeManager) {
        this.mediaPipeManager = mediaPipeManager;
    }

    @Override
    public void detectImage(Image image) {
        mediaPipeManager.detectImage(mapToMPImageInput(image));
    }

    @Override
    public void setDetectionHandListener(DetectionHandListener detectionHandListener) {
        mediaPipeManager.setMPDetectionListener(mapperToCoreListener(detectionHandListener));
    }


    // Правила перевода для связи domain и core модулей

    private MPImageInput mapToMPImageInput(Image image) {
        MPImage mpImage = new BitmapImageBuilder(image.getBitmap()).build();

        return new MPImageInput(mpImage);
    }

    private HandDetected mapToCoreHandDetection(MPDetection mpDetection) {
        float[] coordinates = new float[42];
        HandLandmarkerResult result = mpDetection.getResult();

        if (result.landmarks().size() != 0) {

            Iterator<NormalizedLandmark> iterator = result.landmarks().get(0).iterator();

            for (int i = 0; i < coordinates.length; i += 2) {
                if (iterator.hasNext()) {
                    NormalizedLandmark landmark = iterator.next();
                    coordinates[i] = landmark.x();
                    coordinates[i + 1] = landmark.y();
                } else {
                    break;
                }
            }
        }

        return new HandDetected(coordinates);
    }

    private MPDetectionListener mapperToCoreListener(DetectionHandListener detectionHandListener) {
        return new MPDetectionListener() {
            @Override
            public void detect(MPDetection mpDetection) {
                detectionHandListener.detect(mapToCoreHandDetection(mpDetection));
            }

            @Override
            public void error(Exception exception) {
                detectionHandListener.error(exception);
            }
        };
    }
}
