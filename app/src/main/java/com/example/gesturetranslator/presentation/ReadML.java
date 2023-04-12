package com.example.gesturetranslator.presentation;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Surface;

import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.task.core.BaseOptions;
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions;
import org.tensorflow.lite.task.gms.vision.classifier.Classifications;
import org.tensorflow.lite.task.gms.vision.classifier.ImageClassifier;

import java.util.Comparator;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ReadML {
    private static Context context;
    private static MLReaderListener mlReaderListener;
    private static boolean ban = false;

    interface MLReaderListener {
        void read(List<Category> categories);
    }

    public static void setMlReaderListener(MLReaderListener mlReaderListener) {
        ReadML.mlReaderListener = mlReaderListener;
    }

    public static void readMl(Context context, Bitmap image, int rotation) {
        if (ban) return;
        ReadML.context = context;
        ban = true;
        Observable<List<Category>> observable = Observable.defer(() -> new PredictMLObservable(image, rotation));

        Observer<List<Category>> observer = new Observer<List<Category>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<Category> categories) {
                if (mlReaderListener != null) {
                    mlReaderListener.read(categories);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                ReadML.ban = false;
            }
        };

        observable.subscribeOn(Schedulers.io())
                .subscribe(observer);

    }


    static class PredictMLObservable implements ObservableSource<List<Category>> {
        private final static String TAG = "PredictMLObservable";
        private final Bitmap image;
        private final int rotation;

        public PredictMLObservable(Bitmap image, int rotation) {
            this.image = image;
            this.rotation = rotation;
        }

        @Override
        public void subscribe(@io.reactivex.rxjava3.annotations.NonNull Observer<? super List<Category>> observer) {
            observer.onNext(classify(image, rotation));
            observer.onComplete();
        }

        ImageClassifier imageClassifer = null;

        private void setupClassifier() {
            ImageClassifier.ImageClassifierOptions.Builder optionsBuilder = ImageClassifier.ImageClassifierOptions.builder()
                    .setScoreThreshold(0.01f)
                    .setMaxResults(29);

            BaseOptions.Builder baseOptionsBuilder = BaseOptions.builder().setNumThreads(2);

            optionsBuilder.setBaseOptions(baseOptionsBuilder.build());

            String modelName = "MobileNetV2.tflite";

            try {
                imageClassifer = ImageClassifier.createFromFileAndOptions(context, modelName, optionsBuilder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private List<Category> classify(Bitmap image, int rotation) {
            if (imageClassifer == null) {
                setupClassifier();
            }

            ImageProcessor imageProcessor = new ImageProcessor.Builder().build();

            TensorImage tensorImage = imageProcessor.process(TensorImage.fromBitmap(image));

            ImageProcessingOptions imageProcessingOptions = ImageProcessingOptions.builder()
                    .setOrientation(getOrientationFromRotation(rotation))
                    .build();

            List<Classifications> results = imageClassifer.classify(tensorImage, imageProcessingOptions);

            List<Category> categories = null;

            Log.e(TAG, "results size: " + results.size());
            if (results.size() != 0) {
                categories = results.get(0).getCategories();
                if (categories != null && categories.size() >= 2) {
                    try {
                        categories.sort(new Comparator<Category>() {
                            @Override
                            public int compare(Category category, Category t1) {
                                return (int) ((category.getScore() - t1.getScore()) * 100);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    printCategorys(categories);
                }
                Log.e(TAG, "results categories: " + results.get(0).getCategories().size());
//                if (results.get(0).getCategories().size() != 0) {
//                    Log.e(TAG, "label: " + categories.get(0).getLabel() + " score: " + categories.get(0).getScore());
//                }
            }
            return categories;
        }

        private ImageProcessingOptions.Orientation getOrientationFromRotation(int rotation) {
            switch (rotation) {
                case Surface.ROTATION_270:
                    return ImageProcessingOptions.Orientation.BOTTOM_RIGHT;
                case Surface.ROTATION_180:
                    return ImageProcessingOptions.Orientation.RIGHT_BOTTOM;
                case Surface.ROTATION_90:
                    return ImageProcessingOptions.Orientation.TOP_LEFT;
                default:
                    return ImageProcessingOptions.Orientation.RIGHT_TOP;
            }
        }

        private void printCategorys(List<Category> categories) {
            for (int i = 0; i < categories.size(); i++) {
                Log.e(TAG, "category[" + i + "]: " + categories.get(i).getLabel() + " " + categories.get(i).getScore());
            }
        }
    }
}
