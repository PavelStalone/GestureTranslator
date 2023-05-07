package com.ortin.gesturetranslator.domain.repository;

import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener;

public interface LoadImageRepository {
    void loadImages(LoadImagesListener loadImagesListener);
}
