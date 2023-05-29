package com.ortin.gesturetranslator.domain.repository;

import androidx.lifecycle.LifecycleOwner;

import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener;

public interface LoadImageRepository {
    void loadImages(LoadImagesListener loadImagesListener, LifecycleOwner lifecycleOwner);

    void setStatusFlashlight(boolean mode);
}
