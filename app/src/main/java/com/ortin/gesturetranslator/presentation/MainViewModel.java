package com.ortin.gesturetranslator.presentation;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.ortin.gesturetranslator.domain.listeners.DetectionHandListener;
import com.ortin.gesturetranslator.domain.listeners.LoadImagesListener;
import com.ortin.gesturetranslator.domain.models.CameraFacingSettings;
import com.ortin.gesturetranslator.domain.models.CoordinateClassification;
import com.ortin.gesturetranslator.domain.models.Image;
import com.ortin.gesturetranslator.domain.models.ImageDetected;
import com.ortin.gesturetranslator.domain.usecases.DetectHandUseCase;
import com.ortin.gesturetranslator.domain.usecases.LoadImageUseCase;
import com.ortin.gesturetranslator.domain.usecases.RecognizeCoordinateUseCase;
import com.ortin.gesturetranslator.domain.usecases.WordCompileUseCase;
import com.ortin.gesturetranslator.models.MainFrameState;
import com.ortin.gesturetranslator.models.PredictState;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

@HiltViewModel
public class MainViewModel extends ViewModel implements LoadImagesListener, DetectionHandListener {
    private static final String TAG = "MainViewModel";

    private LoadImageUseCase loadImageUseCase;
    private WordCompileUseCase wordCompileUseCase;
    private DetectHandUseCase detectHandUseCase;
    private RecognizeCoordinateUseCase recognizeCoordinateUseCase;

    private final MutableLiveData<MainFrameState> mainLiveData = new MutableLiveData<>();
    private final MutableLiveData<PredictState> predictLiveData = new MutableLiveData<>();

    @Inject
    public MainViewModel(LoadImageUseCase loadImageUseCase, RecognizeCoordinateUseCase recognizeCoordinateUseCase, WordCompileUseCase wordCompileUseCase, DetectHandUseCase detectHandUseCase) {
        this.loadImageUseCase = loadImageUseCase;
        this.recognizeCoordinateUseCase = recognizeCoordinateUseCase;
        this.wordCompileUseCase = wordCompileUseCase;
        this.detectHandUseCase = detectHandUseCase;

        init();
    }

    private void init() {
        mainLiveData.setValue(new MainFrameState());
        predictLiveData.setValue(new PredictState());

        wordCompileUseCase.clearState();
    }

    public void startRealTimeImagining(LifecycleOwner lifecycleOwner) {
        loadImageUseCase.execute(this, lifecycleOwner, CameraFacingSettings.LENS_FACING_BACK);
        detectHandUseCase.setMPDetectionListener(this);
    }

    @Override
    public void getImage(Image image) {
        Bitmap bitmap = image.getBitmap();

        PredictState predictState = predictLiveData.getValue();
        predictLiveData.setValue(new PredictState(bitmap, predictState.getPredictWord(), predictState.getPredictLetter(), predictState.getCoordinateHand()));

        if (mainLiveData.getValue().isRealtimeButton())
            detectHandUseCase.detectLiveStream(image.getBitmap());
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n", "CheckResult"})
    @Override
    public void detect(ImageDetected imageDetected) {
        if (imageDetected != null) {
            CoordinateClassification coordinateClassification = recognizeCoordinateUseCase.execute(imageDetected);

            if (coordinateClassification.getPercent() > 30f) {
                wordCompileUseCase.addLetter(coordinateClassification.getLabel());
            }

            String predictLetter = String.format("%s", coordinateClassification.getLabel());

            PredictState predictState = predictLiveData.getValue();
            Observable.just(predictState).subscribeOn(AndroidSchedulers.mainThread()).subscribe(t -> {
                predictLiveData.setValue(new PredictState(t.getImageFromCamera(), wordCompileUseCase.getWord(), predictLetter, imageDetected.getCoordinates()));
            });
        } else {
            PredictState predictState = predictLiveData.getValue();
            Observable.just(predictState).subscribeOn(AndroidSchedulers.mainThread()).subscribe(t -> {
                predictLiveData.setValue(new PredictState(t.getImageFromCamera(), t.getPredictWord(), "", null));
            });

        }
    }

    @Override
    public void error(Exception exception) {
        Log.e(TAG, "Error!  [!]");
        exception.printStackTrace();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        offFlashLight();
        Log.e(TAG, "cleared [!]");
    }

    public void onFlashLight() {
        loadImageUseCase.setStatusFlashlight(true);
        MainFrameState mainFrameState = mainLiveData.getValue();
        mainLiveData.setValue(new MainFrameState(true, mainFrameState.isRealtimeButton(), mainFrameState.getBottomSheetBehavior()));
    }

    public void offFlashLight() {
        loadImageUseCase.setStatusFlashlight(false);
        MainFrameState mainFrameState = mainLiveData.getValue();
        mainLiveData.setValue(new MainFrameState(false, mainFrameState.isRealtimeButton(), mainFrameState.getBottomSheetBehavior()));
    }

    public void onStartRealTimeButton() {
        MainFrameState mainFrameState = mainLiveData.getValue();
        mainLiveData.setValue(new MainFrameState(mainFrameState.isFlashlight(), true, BottomSheetBehavior.STATE_COLLAPSED));
        PredictState predictState = predictLiveData.getValue();
        predictLiveData.setValue(new PredictState(predictState.getImageFromCamera(), "", predictState.getPredictLetter(), predictState.getCoordinateHand()));
        wordCompileUseCase.clearState();
    }

    public void onStopRealTimeButton() {
        MainFrameState mainFrameState = mainLiveData.getValue();
        mainLiveData.setValue(new MainFrameState(mainFrameState.isFlashlight(), false, BottomSheetBehavior.STATE_EXPANDED));
    }

    public void bottomSheetCollapsed() {
        onStartRealTimeButton();
    }

    public void bottomSheetExpanded() {
        onStopRealTimeButton();
    }

    public LiveData<MainFrameState> getMainLiveData() {
        return mainLiveData;
    }

    public LiveData<PredictState> getPredictLiveData() {
        return predictLiveData;
    }
}
