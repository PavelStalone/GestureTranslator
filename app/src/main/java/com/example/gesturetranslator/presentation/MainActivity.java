package com.example.gesturetranslator.presentation;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.gesturetranslator.domain.models.Image;
import com.example.gesturetranslator.domain.listeners.LoadImagesInterface;
import com.example.gesturetranslator.domain.usecases.LoadImageUseCase;
import com.example.gesturetranslator.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;

import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.task.gms.vision.TfLiteVision;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements LoadImagesInterface {

    private static final String TAG = "MainActivity";
    private static final int PERMISSION_REQUEST_CAMERA = 23;
    private ActivityMainBinding binding;

    private boolean grayMode;

    @Inject
    LoadImageUseCase loadImageUseCase;

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
            //starting();
            loadImageUseCase.execute(this);
        }
    }


    Iterator<String> iterator;

    public void starting() {
        String[] paths = getFilesFromPath("Russia_test");

        Handler handler = new Handler();

        if (iterator == null) {
            iterator = Arrays.stream(paths).iterator();
        }

        if (iterator.hasNext()) {
            Bitmap bitmap1 = loadImageFromAsset("Russia_test/" + iterator.next());
            binding.preview.setImageBitmap(bitmap1);
            ReadML.readMl(getApplicationContext(), bitmap1, 0);
        } else {
            iterator = null;
            starting();
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                starting();
            }
        };

        handler.postDelayed(runnable, 5000);
    }

    public Bitmap loadImageFromAsset(String path) {
        try {
            InputStream ims = getAssets().open(path);
            Bitmap bitmap = BitmapFactory.decodeStream(ims);
            return bitmap;
        } catch (Exception ex) {
            return null;
        }
    }

    private String[] getFilesFromPath(String path) {
        AssetManager myAssetManager = getApplicationContext().getAssets();
        try {
            String[] Files = myAssetManager.list(path);
            return Files;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
            loadImageUseCase.execute(this);
        }
    }

    @Override
    public void getImage(Image image) {
        Bitmap bitmap = image.getBitmap();
        int rotation = image.getRotaion();

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

        binding.preview.setRotation(rotation);
        binding.preview.setImageBitmap(bitmap);
    }

    @Override
    public void error(Exception exception) {
        exception.printStackTrace();
    }
}