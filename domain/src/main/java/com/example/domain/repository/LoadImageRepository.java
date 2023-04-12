package com.example.domain.repository;

import com.example.domain.listeners.LoadImagesInterface;
import com.example.domain.models.Image;

public interface LoadImageRepository {
    Image loadImages(LoadImagesInterface loadImagesInterface);
}
