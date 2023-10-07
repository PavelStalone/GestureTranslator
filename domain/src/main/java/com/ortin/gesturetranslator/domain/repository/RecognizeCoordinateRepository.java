package com.ortin.gesturetranslator.domain.repository;

import androidx.annotation.NonNull;
import com.ortin.gesturetranslator.domain.models.CoordinateClassification;
import com.ortin.gesturetranslator.domain.models.ImageDetected;

public interface RecognizeCoordinateRepository {
    CoordinateClassification recognise(@NonNull ImageDetected imageDetected);
}
