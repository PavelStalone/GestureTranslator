package com.ortin.gesturetranslator.models;

import android.graphics.Bitmap;

import java.util.List;

public class PredictState {
    private Bitmap imageFromCamera = null;
    private String predictWord = "";
    private String predictLetter = "";
    private List<Float> coordinateHand = null;

    public PredictState() {}

    public PredictState(Bitmap imageFromCamera, String predictWord, String predictLetter, List<Float> coordinateHand) {
        this.imageFromCamera = imageFromCamera;
        this.predictWord = predictWord;
        this.predictLetter = predictLetter;
        this.coordinateHand = coordinateHand;
    }

    public Bitmap getImageFromCamera() {
        return imageFromCamera;
    }

    public String getPredictWord() {
        return predictWord;
    }

    public List<Float> getCoordinateHand() {
        return coordinateHand;
    }

    public String getPredictLetter() {
        return predictLetter;
    }
}
