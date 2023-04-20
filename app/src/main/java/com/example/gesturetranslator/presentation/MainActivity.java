package com.example.gesturetranslator.presentation;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.gesturetranslator.databinding.MainFrameBinding;
import com.example.gesturetranslator.domain.listeners.LoadImagesListener;
import com.example.gesturetranslator.domain.listeners.RecognizeImageListener;
import com.example.gesturetranslator.domain.models.Image;
import com.example.gesturetranslator.domain.models.ImageClassifications;
import com.example.gesturetranslator.domain.usecases.LoadImageUseCase;
import com.example.gesturetranslator.domain.usecases.RecognizeImageUseCase;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements LoadImagesListener, RecognizeImageListener {

    private static final String TAG = "MainActivity";
    private static final int PERMISSION_REQUEST_CAMERA = 23;
    private MainFrameBinding binding;
    private BottomSheetBehavior bottomSheetBehavior;

    private boolean grayMode;

    private MainViewModel mainViewModel;

    @Inject
    LoadImageUseCase loadImageUseCase;

    @Inject
    RecognizeImageUseCase recognizeImageUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainFrameBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        //mainViewModel = new ViewModelProvider(this, new MainViewModelFactory()).get(MainViewModel.class);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();
        initListeners();
        start();
    }

    public void init() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetBehaviorLayout.bottomSheetBehavior);

        binding.bottomSheetBehaviorLayout.bottomSheetBehavior.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.bottomSheetBehaviorLayout.bottomSheetBehavior.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                bottomSheetBehavior.setPeekHeight(displayMetrics.heightPixels - binding.imageWithPredict.preview.getBottom() - binding.imageWithPredict.wordPredictTV.getHeight());
            }
        });
    }

    private void start() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
        } else {
            //starting();
            loadImageUseCase.execute(this);
            recognizeImageUseCase.setOnRecogniseListener(this);
        }
    }

    boolean flag = false;

    private void initListeners() {
//        binding.graySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                grayMode = b;
//                Log.e(TAG, "onCheckedChanged: " + grayMode);
//            }
//        });
        binding.controlMenu.realTimeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = !flag;
                if (flag) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
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
            binding.imageWithPredict.preview.setImageBitmap(bitmap1);
            //ReadML.readMl(getApplicationContext(), bitmap1, 0);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CAMERA && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            start();
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

        binding.imageWithPredict.preview.setRotation(rotation);
        binding.imageWithPredict.preview.setImageBitmap(bitmap);

//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//
//        bottomSheetBehavior.setPeekHeight(displayMetrics.heightPixels - binding.imageWithPredict.preview.getBottom() - binding.imageWithPredict.wordPredictTV.getHeight());

        recognizeImageUseCase.execute(image);
    }

    @Override
    public void recognise(ImageClassifications imageClassifications) {
        binding.imageWithPredict.wordPredictTV.setText(String.format("%s %.2f", imageClassifications.getLabel(), imageClassifications.getPercent()) + "%");
    }

    @Override
    public void error(Exception exception) {
        Log.e(TAG, "Error!  [!]");
        exception.printStackTrace();
    }
}