package com.example.gesturetranslator.core.tensor_flow_lite_manager;

import android.content.Context;
import android.util.Log;
import android.view.Surface;

import com.example.gesturetranslator.core.tensor_flow_lite_manager.listeners.TFLRecognizeListener;
import com.example.gesturetranslator.core.tensor_flow_lite_manager.models.TFLImage;
import com.example.gesturetranslator.core.tensor_flow_lite_manager.models.TFLImageClasification;

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

public class TFLManagerImpl implements TFLManager {
    private final Context context;

    private TFLRecognizeListener tflRecognizeListener;
    private static boolean ban = false;

    public static final String[] LABEL = new String[]{"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Ь", "Ы", "Э", "Ю", "Я"};

    public TFLManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public void recogniseImage(TFLImage tflImage) {
        if (ban) return;
        ban = true;
        Observable<TFLImageClasification> observable = Observable.defer(() -> new PredictMLObservable(tflImage));

        Observer<TFLImageClasification> observer = new Observer<TFLImageClasification>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull TFLImageClasification tflImageClasification) {
                if (tflRecognizeListener != null) {
                    tflRecognizeListener.recognise(tflImageClasification);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (tflRecognizeListener != null) {
                    tflRecognizeListener.error((Exception) e);
                }
            }

            @Override
            public void onComplete() {
                ban = false;
            }
        };

        observable.subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    @Override
    public void setTflRecogniseListener(TFLRecognizeListener tflRecognizeListener) {
        this.tflRecognizeListener = tflRecognizeListener;
    }

    class PredictMLObservable implements ObservableSource<TFLImageClasification> {
        private final static String TAG = "PredictMLObservable";
        private final TFLImage tflImage;

        public PredictMLObservable(TFLImage tflImage) {
            this.tflImage = tflImage;
        }

        @Override
        public void subscribe(@io.reactivex.rxjava3.annotations.NonNull Observer<? super TFLImageClasification> observer) {
            observer.onNext(classify(tflImage));
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

        private TFLImageClasification classify(TFLImage tflImage) {
            if (imageClassifer == null) {
                setupClassifier();
            }

            ImageProcessingOptions imageProcessingOptions = ImageProcessingOptions.builder()
                    .setOrientation(getOrientationFromRotation(tflImage.getRotation()))
                    .build();

            List<Classifications> results = imageClassifer.classify(tflImage.getTensorImage(), imageProcessingOptions);

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
            }
            return new TFLImageClasification(LABEL[categories.get(0).getIndex()], categories.get(0).getScore() * 100);
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
