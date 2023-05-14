package com.ortin.gesturetranslator.feature.managers.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;

import androidx.annotation.NonNull;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.ortin.gesturetranslator.feature.managers.camera.listeners.CameraListener;
import com.ortin.gesturetranslator.feature.managers.camera.models.ImageFromCamera;
import com.ortin.gesturetranslator.feature.translators.YUVtoRGB;
import com.google.common.util.concurrent.ListenableFuture;

public class CameraManagerImpl implements CameraManager{

    private final Context context;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private final YUVtoRGB translator = new YUVtoRGB();
    private Bitmap bitmap;

    public CameraManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(CameraListener cameraListener, LifecycleOwner lifecycleOwner) {
        cameraProviderFuture = ProcessCameraProvider.getInstance(context);
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                    ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                            .setTargetAspectRatio(AspectRatio.RATIO_4_3)
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

                            Matrix matrix = new Matrix();
                            matrix.postRotate(image.getImageInfo().getRotationDegrees());
                            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

                            ImageFromCamera imageFromCamera = new ImageFromCamera(bitmap, image.getImageInfo().getRotationDegrees());
                            if (cameraListener != null) cameraListener.getImage(imageFromCamera);

                            image.close();
                        }
                    });

                    cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, imageAnalysis);

                } catch (Exception e) {
                    if (cameraListener != null) cameraListener.error(e);
                }
            }
        }, ContextCompat.getMainExecutor(context));
    }
}
