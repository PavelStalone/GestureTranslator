package com.example.gesturetranslator.core.managers.mediapipe_manager;

import android.content.Context;

import com.example.gesturetranslator.core.managers.mediapipe_manager.listeners.MPDetectionListener;
import com.example.gesturetranslator.core.managers.mediapipe_manager.models.MPDetection;
import com.example.gesturetranslator.core.managers.mediapipe_manager.models.MPImageInput;
import com.google.mediapipe.tasks.core.BaseOptions;
import com.google.mediapipe.tasks.vision.core.RunningMode;
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker;
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MediaPipeManagerImpl implements MediaPipeManager {
    private final Context context;

    private MPDetectionListener mpDetectionListener;
    private static boolean ban = false;

    public MediaPipeManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public void detectImage(MPImageInput mpImageInput) {
        if (ban) return;
        ban = true;
        Observable<MPDetection> observable = Observable.defer(() -> new PredictMPObservable(mpImageInput));

        Observer<MPDetection> observer = new Observer<MPDetection>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull MPDetection mpClasification) {
                if (mpDetectionListener != null) {
                    mpDetectionListener.detect(mpClasification);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (mpDetectionListener != null) {
                    mpDetectionListener.error((Exception) e);
                }
                ban = false;
            }

            @Override
            public void onComplete() {
                ban = false;
            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void setMPDetectionListener(MPDetectionListener mpDetectionListener) {
        this.mpDetectionListener = mpDetectionListener;
    }

    class PredictMPObservable implements ObservableSource<MPDetection> {
        private final static String TAG = "PredictMPObservable";
        private final MPImageInput MPImageInput;

        public PredictMPObservable(MPImageInput MPImageInput) {
            this.MPImageInput = MPImageInput;
        }

        @Override
        public void subscribe(@NonNull Observer<? super MPDetection> observer) {
            observer.onNext(detect(MPImageInput));
            observer.onComplete();
        }

        HandLandmarker handLandmarker = null;

        private void setupBuilder() {
            String modelName = "hand_landmarker.task";
            BaseOptions.Builder baseOptionsBuilder = BaseOptions.builder().setModelAssetPath(modelName);

            BaseOptions baseOptions = baseOptionsBuilder.build();

            HandLandmarker.HandLandmarkerOptions.Builder optionsBuilder = HandLandmarker.HandLandmarkerOptions.builder()
                    .setBaseOptions(baseOptions)
                    .setMinHandDetectionConfidence(0.5f) // Минимальная оценка достоверности для обнаружения рук, которая считается успешной в модели обнаружения ладоней
                    .setMinTrackingConfidence(0.5f) // Минимальная оценка достоверности для того, чтобы отслеживание рук считалось успешным
                    .setMinHandPresenceConfidence(0.5f) // Минимальная оценка достоверности для оценки присутствия руки в модели обнаружения ориентира руки
                    .setNumHands(1) // Максимальное количество рук, обнаруженных Детектором ориентиров рук
                    .setRunningMode(RunningMode.IMAGE); // Устанавливает режим работы для задачи ручного ориентира

            HandLandmarker.HandLandmarkerOptions options = optionsBuilder.build();

            handLandmarker = HandLandmarker.createFromOptions(context, options);
        }

        private MPDetection detect(MPImageInput mpImageInput) {
            if (handLandmarker == null) {
                setupBuilder();
            }

            HandLandmarkerResult result = handLandmarker.detect(mpImageInput.getMpImage());

            return new MPDetection(result);
        }
    }
}
