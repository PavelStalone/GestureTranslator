package com.ortin.gesturetranslator.domain.listeners;

import com.ortin.gesturetranslator.domain.models.Image;

public interface LoadImagesListener {
    void getImage(Image image);
    void error(Exception exception);
}
