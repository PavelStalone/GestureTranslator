package com.ortin.gesturetranslator.domain.models;

import android.graphics.Bitmap;

public class Image {
    final Bitmap bitmap;
    final int rotation;

    public Image(Bitmap bitmap, int rotation) {
        this.bitmap = bitmap;
        this.rotation = rotation;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getRotation() {
        return rotation;
    }
}
