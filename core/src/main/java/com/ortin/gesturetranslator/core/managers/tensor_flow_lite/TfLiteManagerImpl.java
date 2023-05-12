package com.ortin.gesturetranslator.core.managers.tensor_flow_lite;

import android.content.Context;
import android.util.Log;
import android.view.Surface;

import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.listeners.TfLiteRecognizeListener;
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.models.TfLiteImage;
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.models.TfLiteImageClassification;
import com.google.android.gms.tasks.OnSuccessListener;

import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.task.core.BaseOptions;
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions;
import org.tensorflow.lite.task.gms.vision.TfLiteVision;
import org.tensorflow.lite.task.gms.vision.classifier.Classifications;
import org.tensorflow.lite.task.gms.vision.classifier.ImageClassifier;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TfLiteManagerImpl implements TfLiteManager {
    private final Context context;

    private TfLiteRecognizeListener tfLiteRecognizeListener;
    private static boolean ban = false;

    public static final String[] LABELS = new String[]{"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Ы", "Ь", "Э", "Ю", "Я"};
    private final String modelName;

    public TfLiteManagerImpl(Context context, String modelName) {
        this.context = context;
        this.modelName = modelName;
        ban = true;
        TfLiteVision.initialize(context).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                ban = false;
            }
        });
    }

    @Override
    public void recogniseImage(TfLiteImage tfLiteImage) {
        if (ban) return;
        ban = true;
        Observable<TfLiteImageClassification> observable = Observable.defer(() -> new PredictMLObservable(tfLiteImage));

        Observer<TfLiteImageClassification> observer = new Observer<TfLiteImageClassification>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull TfLiteImageClassification tflImageClasification) {
                if (tfLiteRecognizeListener != null) {
                    tfLiteRecognizeListener.recognise(tflImageClasification);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (tfLiteRecognizeListener != null) {
                    tfLiteRecognizeListener.error((Exception) e);
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
    public void setTflRecogniseListener(TfLiteRecognizeListener tfLiteRecognizeListener) {
        this.tfLiteRecognizeListener = tfLiteRecognizeListener;
    }

    class PredictMLObservable implements ObservableSource<TfLiteImageClassification> {
        private final static String TAG = "PredictMLObservable";
        private final TfLiteImage tfLiteImage;

        public PredictMLObservable(TfLiteImage tfLiteImage) {
            this.tfLiteImage = tfLiteImage;
        }

        @Override
        public void subscribe(@NonNull Observer<? super TfLiteImageClassification> observer) {
            observer.onNext(classify(tfLiteImage));
            observer.onComplete();
        }

        ImageClassifier imageClassifer = null;

        private void setupClassifier() {
            ImageClassifier.ImageClassifierOptions.Builder optionsBuilder = ImageClassifier.ImageClassifierOptions.builder()
                    .setScoreThreshold(0.01f)
                    .setMaxResults(29);

            BaseOptions.Builder baseOptionsBuilder = BaseOptions.builder().setNumThreads(3);

            optionsBuilder.setBaseOptions(baseOptionsBuilder.build());

            try {
                imageClassifer = ImageClassifier.createFromFileAndOptions(context, modelName, optionsBuilder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private TfLiteImageClassification classify(TfLiteImage tfLiteImage) {
            if (imageClassifer == null) {
                setupClassifier();
            }

            ImageProcessingOptions imageProcessingOptions = ImageProcessingOptions.builder()
                    .setOrientation(getOrientationFromRotation(tfLiteImage.getRotation()))
                    .build();

            List<Classifications> results = imageClassifer.classify(tfLiteImage.getTensorImage(), imageProcessingOptions);

            List<Category> categories = null;

            Log.e(TAG, "results size: " + results.size());
            if (results.size() != 0) {
                categories = results.get(0).getCategories();
                if (categories != null && categories.size() >= 2) {
                    try {
                        categories = categories.stream().sorted(new Comparator<Category>() {
                            @Override
                            public int compare(Category category, Category t1) {
                                Log.e(TAG, "category Score: " + category.getScore() + " t1 score: " + t1.getScore());
                                return (int) ((t1.getScore() - category.getScore()) * 100);
                            }
                        }).collect(Collectors.toList());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    printCategories(categories);
                }
                Log.e(TAG, "results categories: " + results.get(0).getCategories().size());
            }

            if (categories != null)
                return new TfLiteImageClassification(LABELS[categories.get(0).getIndex()], categories.get(0).getScore() * 100);
            return null;
        }

        private ImageProcessingOptions.Orientation getOrientationFromRotation(int rotation) {
            return switch (rotation) {
                case Surface.ROTATION_270 -> ImageProcessingOptions.Orientation.BOTTOM_RIGHT;
                case Surface.ROTATION_180 -> ImageProcessingOptions.Orientation.RIGHT_BOTTOM;
                case Surface.ROTATION_90 -> ImageProcessingOptions.Orientation.TOP_LEFT;
                default -> ImageProcessingOptions.Orientation.RIGHT_TOP;
            };
        }

        private void printCategories(List<Category> categories) {
            for (int i = 0; i < categories.size(); i++) {
                Log.e(TAG, "category[" + i + "]: " + categories.get(i).getLabel() + " " + categories.get(i).getScore());
            }
        }
    }
}
