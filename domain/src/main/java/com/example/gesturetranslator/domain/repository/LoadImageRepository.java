package com.example.gesturetranslator.domain.repository;

import com.example.gesturetranslator.domain.listeners.LoadImagesListener;
import com.example.gesturetranslator.domain.models.Image;

public interface LoadImageRepository {
    void loadImages(LoadImagesListener loadImagesListener);
}
