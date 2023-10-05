package com.ortin.gesturetranslator.core.repository

import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.TfLiteManager
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.listeners.TfLiteRecognizeListener
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.models.TfLiteImage
import com.ortin.gesturetranslator.core.managers.tensor_flow_lite.models.TfLiteImageClassification
import com.ortin.gesturetranslator.domain.listeners.RecognizeImageListener
import com.ortin.gesturetranslator.domain.models.Image
import com.ortin.gesturetranslator.domain.models.ImageClassification
import com.ortin.gesturetranslator.domain.repository.RecognizeImageRepository
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage

class RecognizeImageRepositoryImpl(private val tfLiteManager: TfLiteManager) : RecognizeImageRepository {
    override fun recogniseImage(image: Image) =
        tfLiteManager.recogniseImage(mapToCoreImage(image))

    override fun setRecogniseListener(recognizeImageListener: RecognizeImageListener) =
        tfLiteManager.setTflRecogniseListener(mapToCoreListener(recognizeImageListener))

    // Правила перевода для связи domain и core модулей
    private fun mapToCoreImage(image: Image): TfLiteImage {
        val imageProcessor = ImageProcessor.Builder().build()
        val tensorImage = imageProcessor.process(TensorImage.fromBitmap(image.bitmap))
        return TfLiteImage(tensorImage, image.rotation)
    }

    private fun mapToCoreImageClassification(tfLiteImageClassification: TfLiteImageClassification): ImageClassification {
        return ImageClassification(
            tfLiteImageClassification.label,
            tfLiteImageClassification.percent
        )
    }

    private fun mapToCoreListener(recognizeImageListener: RecognizeImageListener): TfLiteRecognizeListener {
        return object : TfLiteRecognizeListener {
            override fun recognise(tfLiteImageClassification: TfLiteImageClassification) {
                recognizeImageListener.recognise(
                    mapToCoreImageClassification(
                        tfLiteImageClassification
                    )
                )
            }

            override fun error(exception: Exception) {
                recognizeImageListener.error(exception)
            }
        }
    }
}
