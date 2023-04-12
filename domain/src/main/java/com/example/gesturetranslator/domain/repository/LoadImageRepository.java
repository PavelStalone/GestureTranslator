package com.example.gesturetranslator.domain.repository;

import com.example.gesturetranslator.domain.listeners.LoadImagesInterface;
import com.example.gesturetranslator.domain.models.Image;

public interface LoadImageRepository {
    Image loadImages(LoadImagesInterface loadImagesInterface);
}
