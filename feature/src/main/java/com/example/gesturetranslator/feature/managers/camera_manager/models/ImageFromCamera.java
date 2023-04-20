package com.example.gesturetranslator.feature.managers.camera_manager.models;

import android.graphics.Bitmap;

public class ImageFromCamera {
    final Bitmap image;
    final int rotaion;

    public ImageFromCamera(Bitmap image, int rotaion) {
        this.image = image;
        this.rotaion = rotaion;
    }

    public Bitmap getImage() {
        return image;
    }

    public int getRotaion() {
        return rotaion;
    }
}
