package com.ortin.gesturetranslator.core.managers.mediapipe

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.SystemClock
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.framework.image.MPImage
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.core.Delegate
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult
import com.ortin.gesturetranslator.core.managers.mediapipe.listeners.MPDetectionListener
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPImageDetection
import com.ortin.gesturetranslator.core.managers.mediapipe.models.MPVideoDetection
import com.ortin.gesturetranslator.core.managers.mediapipe.models.SettingsModel

class HandLandmarkerHelper(
    val context: Context,
    val modelPath: String,
    var handLandmarkerHelperListener: MPDetectionListener? = null,
    var settingsModel: SettingsModel = SettingsModel(),
) {
    private var handLandmarker: HandLandmarker? = null

    init {
        setupHandLandmarker()
    }

    fun clearHandLandmarker() {
        handLandmarker?.close()
        handLandmarker = null
    }

    fun isClose(): Boolean = handLandmarker == null

    fun setupHandLandmarker() {
        val baseOptionBuilder = BaseOptions.builder()

        when (settingsModel.currentDelegate) {
            DELEGATE_CPU -> {
                baseOptionBuilder.setDelegate(Delegate.CPU)
            }

            DELEGATE_GPU -> {
                baseOptionBuilder.setDelegate(Delegate.GPU)
            }
        }

        baseOptionBuilder.setModelAssetPath(modelPath)

        try {
            val baseOptions = baseOptionBuilder.build()
            val optionsBuilder =
                HandLandmarker.HandLandmarkerOptions.builder()
                    .setBaseOptions(baseOptions)
                    .setMinHandDetectionConfidence(settingsModel.minHandDetectionConfidence) // Минимальная оценка достоверности для обнаружения рук, которая считается успешной в модели обнаружения ладоней
                    .setMinTrackingConfidence(settingsModel.minHandTrackingConfidence) // Минимальная оценка достоверности для того, чтобы отслеживание рук считалось успешным
                    .setMinHandPresenceConfidence(settingsModel.minHandPresenceConfidence) // Минимальная оценка достоверности для оценки присутствия руки в модели обнаружения ориентира руки
                    .setNumHands(settingsModel.maxNumHands) // Максимальное количество рук, обнаруженных Детектором ориентиров рук
                    .setRunningMode(settingsModel.runningMode) // Устанавливает режим работы для задачи ручного ориентира
                    .apply {
                        if (settingsModel.runningMode == RunningMode.LIVE_STREAM) {
                            setResultListener(this@HandLandmarkerHelper::returnLivestreamResult)
                            setErrorListener(this@HandLandmarkerHelper::returnLivestreamError)
                        }
                    }
            val options = optionsBuilder.build()
            handLandmarker = HandLandmarker.createFromOptions(context, options)
        } catch (e: RuntimeException) {
            handLandmarkerHelperListener?.onError(e)
        }
    }

    fun detectLiveStream(
        image: Bitmap
    ) {
        if (settingsModel.runningMode != RunningMode.LIVE_STREAM) {
            throw IllegalArgumentException(
                "Attempting to call detectLiveStream while not using RunningMode.LIVE_STREAM"
            )
        }
        if (handLandmarkerHelperListener == null) {
            throw IllegalStateException(
                "handLandmarkerHelperListener must be set when runningMode is LIVE_STREAM."
            )
        }

        val frameTime = SystemClock.uptimeMillis()
        val mpImage = BitmapImageBuilder(image).build()

        detectAsync(mpImage, frameTime)
    }

    private fun detectAsync(mpImage: MPImage, frameTime: Long) {
        handLandmarker?.detectAsync(mpImage, frameTime)
    }

    fun detectVideoFile(
        videoUri: Uri,
        inferenceIntervalMs: Long
    ): MPVideoDetection? {
        if (settingsModel.runningMode != RunningMode.VIDEO) {
            throw IllegalArgumentException(
                "Attempting to call detectVideoFile while not using RunningMode.VIDEO"
            )
        }

        val startTime = SystemClock.uptimeMillis()
        var didErrorOccurred = false

        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(context, videoUri)
        val videoLengthMs =
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                ?.toLong()

        val firstFrame = retriever.getFrameAtTime(0)
        val width = firstFrame?.width
        val height = firstFrame?.height

        if ((videoLengthMs == null) || (width == null) || (height == null)) return null

        val resultList = mutableListOf<HandLandmarkerResult>()
        val numberOfFrameToRead = videoLengthMs.div(inferenceIntervalMs)

        for (i in 0..numberOfFrameToRead) {
            val timestampMs = i * inferenceIntervalMs // ms

            retriever
                .getFrameAtTime(
                    timestampMs * 1000, // convert from ms to micro-s
                    MediaMetadataRetriever.OPTION_CLOSEST
                )
                ?.let { frame ->
                    val argb8888Frame =
                        if (frame.config == Bitmap.Config.ARGB_8888) frame
                        else frame.copy(Bitmap.Config.ARGB_8888, false)
                    val mpImage = BitmapImageBuilder(argb8888Frame).build()

                    handLandmarker?.detectForVideo(mpImage, timestampMs)
                        ?.let { detectionResult ->
                            resultList.add(detectionResult)
                        } ?: {
                        didErrorOccurred = true
                        handLandmarkerHelperListener?.onError(
                            RuntimeException("com.ortin.gesturetranslator.core.managers.mediapipe.models.MPDetection could not be returned in detectVideoFile")
                        )
                    }
                }
                ?: run {
                    didErrorOccurred = true
                    handLandmarkerHelperListener?.onError(
                        RuntimeException("Frame at specified time could not be retrieved when detecting in video")
                    )
                }
        }
        retriever.release()

        val inferenceTimePerFrameMs =
            (SystemClock.uptimeMillis() - startTime).div(numberOfFrameToRead)

        return if (didErrorOccurred) {
            null
        } else {
            MPVideoDetection(resultList, inferenceTimePerFrameMs, height, width)
        }
    }

    fun detectImage(image: Bitmap): MPImageDetection? {
        if (settingsModel.runningMode != RunningMode.IMAGE) {
            throw IllegalArgumentException(
                "Attempting to call detectImage" +
                        " while not using RunningMode.IMAGE"
            )
        }

        val startTime = SystemClock.uptimeMillis()
        val mpImage = BitmapImageBuilder(image).build()

        handLandmarker?.detect(mpImage)?.also { landmarkResult ->
            return MPImageDetection(
                landmarkResult,
                SystemClock.uptimeMillis() - startTime,
                image.height,
                image.width
            )
        }

        handLandmarkerHelperListener?.onError(
            RuntimeException("Hand Landmarker failed to detect")
        )
        return null
    }

    private fun returnLivestreamResult(
        result: HandLandmarkerResult,
        input: MPImage
    ) {
        val finishTimeMs = SystemClock.uptimeMillis()
        val inferenceTime = finishTimeMs - result.timestampMs()

        handLandmarkerHelperListener?.onResults(
            MPImageDetection(
                result,
                inferenceTime,
                input.height,
                input.width
            )
        )
    }

    private fun returnLivestreamError(error: RuntimeException) {
        handLandmarkerHelperListener?.onError(error)
    }

    companion object {
        const val DELEGATE_CPU = 0
        const val DELEGATE_GPU = 1
        const val DEFAULT_HAND_DETECTION_CONFIDENCE = 0.5F
        const val DEFAULT_HAND_TRACKING_CONFIDENCE = 0.5F
        const val DEFAULT_HAND_PRESENCE_CONFIDENCE = 0.5F
        const val DEFAULT_NUM_HANDS = 1
    }
}
