package com.example.gesturetranslator.feature.camera_manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Size;

import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.example.gesturetranslator.feature.camera_manager.listeners.CameraListener;
import com.example.gesturetranslator.feature.camera_manager.models.ImageFromCamera;
import com.example.gesturetranslator.feature.translators.YUVtoRGB;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class CameraManagerImpl implements CameraManager{

    private final Context context;
    private CameraListener cameraListener;
    ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    YUVtoRGB translator = new YUVtoRGB();
    private Bitmap bitmap;

    public CameraManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(CameraListener cameraListener) {
        this.cameraListener = cameraListener;

        cameraProviderFuture = ProcessCameraProvider.getInstance(context);
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                    ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                            .setTargetResolution(new Size(1024, 768))
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .build();

                    CameraSelector cameraSelector = new CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build();

                    imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(context), new ImageAnalysis.Analyzer() {
                        @Override
                        public void analyze(@NonNull ImageProxy image) {
                            Image img = image.getImage();
                            bitmap = translator.translateYUV(img, context);

                            ImageFromCamera imageFromCamera = new ImageFromCamera(bitmap, image.getImageInfo().getRotationDegrees());
                            if (cameraListener != null) cameraListener.getImage(imageFromCamera);

                            image.close();
                        }
                    });

                    cameraProvider.bindToLifecycle((LifecycleOwner) context, cameraSelector, imageAnalysis);

                } catch (ExecutionException e) {
                    if (cameraListener != null) cameraListener.error(e);
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    if (cameraListener != null) cameraListener.error(e);
                    throw new RuntimeException(e);
                }
            }
        }, ContextCompat.getMainExecutor(context));
    }
}
