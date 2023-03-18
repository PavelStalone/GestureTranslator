package com.example.gesturetranslator;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.Surface;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.gesturetranslator.Translators.YUVtoRGB;
import com.example.gesturetranslator.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.util.concurrent.ListenableFuture;

import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.task.core.BaseOptions;
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions;
import org.tensorflow.lite.task.gms.vision.TfLiteVision;
import org.tensorflow.lite.task.gms.vision.classifier.Classifications;
import org.tensorflow.lite.task.gms.vision.classifier.ImageClassifier;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int PERMISSION_REQUEST_CAMERA = 23;
    private ActivityMainBinding binding;

    ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    YUVtoRGB translator = new YUVtoRGB();

    private Bitmap bitmap;
    private boolean grayMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();
        initListeners();
    }

    private void init() {
        TfLiteVision.initialize(getApplicationContext()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                start();
            }
        });
    }

    private void start() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
        } else {
            initializeCamera();
        }
    }

    private void initListeners() {
        ReadML.setMlReaderListener(new ReadML.MLReaderListener() {
            @Override
            public void read(List<Category> categories) {
                if (categories != null && categories.size() != 0) {
                    Log.e(TAG, "label: " + categories.get(0).getLabel() + " score: " + categories.get(0).getScore());
                    binding.wordPredictTV.setText(Constant.LABEL[categories.get(0).getIndex()] + " " + categories.get(0).getScore() + "%");
                } else {
                    binding.wordPredictTV.setText("");
                }
            }
        });
        binding.graySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                grayMode = b;
                Log.e(TAG, "onCheckedChanged: " + grayMode);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CAMERA && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initializeCamera();
        }
    }

    private void initializeCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
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

                    imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(MainActivity.this), new ImageAnalysis.Analyzer() {
                        @Override
                        public void analyze(@NonNull ImageProxy image) {
                            Image img = image.getImage();
                            bitmap = translator.translateYUV(img, MainActivity.this);

                            if (grayMode) {
                                int size = bitmap.getWidth() * bitmap.getHeight();
                                int[] pixels = new int[size];
                                bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

                                for (int i = 0; i < size; i++) {
                                    int color = pixels[i];
                                    int r = color >> 16 & 0xff;
                                    int g = color >> 8 & 0xff;
                                    int b = color & 0xff;
                                    int gray = (r + g + b) / 3;
                                    pixels[i] = 0xff000000 | gray << 16 | gray << 8 | gray;
                                }

                                bitmap.setPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
                            }

                            binding.preview.setRotation(image.getImageInfo().getRotationDegrees());
                            binding.preview.setImageBitmap(bitmap);

                            ReadML.readMl(getApplicationContext(), bitmap, image.getImageInfo().getRotationDegrees());

                            image.close();
                        }
                    });

                    cameraProvider.bindToLifecycle(MainActivity.this, cameraSelector, imageAnalysis);

                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, ContextCompat.getMainExecutor(this));
    }
}