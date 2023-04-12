package com.example.domain.listeners;

import com.example.domain.models.Image;

public interface LoadImagesInterface {
    void getImage(Image image);
    void error(Exception exception);
}
