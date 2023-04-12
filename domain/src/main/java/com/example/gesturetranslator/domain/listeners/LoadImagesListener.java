package com.example.gesturetranslator.domain.listeners;

import com.example.gesturetranslator.domain.models.Image;

public interface LoadImagesListener {
    void getImage(Image image);
    void error(Exception exception);
}
