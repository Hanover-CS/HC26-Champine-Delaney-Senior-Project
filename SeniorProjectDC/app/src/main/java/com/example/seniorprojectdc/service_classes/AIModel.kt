package com.example.seniorprojectdc.service_classes
/*
This creates a class for the pyTorch AI model so that it can be easily used
with the rest of the apps functions
 */
import android.content.Context
import android.graphics.Bitmap
import org.pytorch.IValue
import org.pytorch.LiteModuleLoader
import org.pytorch.Module
import org.pytorch.torchvision.TensorImageUtils

class AIModel (private val context : Context) {
    private val module: Module =
        LiteModuleLoader.loadModuleFromAsset(context.assets, "app_model.ptl")

    private val labels = listOf(
        "bumble_bee",
        "bold_jumping_spider",
        "honey_bee",
        "japanese_beetle",
        "luna_moth"
    )

    fun classify(bitmap: Bitmap): Pair<String, Float> {
        val inputTensor = TensorImageUtils.bitmapToFloat32Tensor(
            bitmap,
            floatArrayOf(0.485f, 0.456f, 0.406f), // mean
            floatArrayOf(0.229f, 0.224f, 0.225f)  // std
        )

        val output = module.forward(IValue.from(inputTensor)).toTensor()
        val scores = output.dataAsFloatArray

        val maxIdx = scores.indices.maxBy { scores[it] }
        val confidence = scores[maxIdx]
        val label = labels[maxIdx]

        return Pair(label, confidence)
    }
}