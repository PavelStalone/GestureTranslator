package com.example.gesturetranslator.domain.models;

import android.graphics.Bitmap;

public class Image {
    final Bitmap bitmap;
    final int rotaion;

    public Image(Bitmap bitmap, int rotaion) {
        this.bitmap = bitmap;
        this.rotaion = rotaion;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getRotaion() {
        return rotaion;
    }
}
