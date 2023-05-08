package com.ortin.gesturetranslator.domain.repository;

import com.ortin.gesturetranslator.domain.models.CoordinateClassification;
import com.ortin.gesturetranslator.domain.models.HandDetected;

public interface RecognizeCoordinateRepository {
    CoordinateClassification recognise(HandDetected handDetected);
}
