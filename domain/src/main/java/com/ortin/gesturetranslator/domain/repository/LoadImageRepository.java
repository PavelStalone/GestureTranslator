package com.ortin.gesturetranslator.domain.repository;

import android.app.Fragment;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener;

public interface LoadImageRepository {
    void loadImages(LoadImagesListener loadImagesListener, LifecycleOwner lifecycleOwner);
}
