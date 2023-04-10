package com.example.domain.models;

import android.graphics.Bitmap;

public class Image {
    final Bitmap bitmap;
    final int rotaion;

    public Image(Bitmap bitmap, int rotaion) {
        this.bitmap = bitmap;
        this.rotaion = rotaion;
    }
}
