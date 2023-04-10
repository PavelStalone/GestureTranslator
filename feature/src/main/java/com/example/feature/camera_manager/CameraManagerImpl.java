package com.example.feature.camera_manager;

import android.content.Context;

import com.example.feature.camera_manager.models.ImageFromCamera;

public class CameraManagerImpl implements CameraManager{

    private final Context context;

    public CameraManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public ImageFromCamera loadImage() {
        return null;
    }
}
