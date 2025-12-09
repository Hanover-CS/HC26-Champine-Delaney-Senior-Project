package com.example.seniorprojectdc.service_classes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.math.min

/*
This function takes the image information given by the gallery or the camera and turns
it into a URI which is smaller and easier to store in a DB than the image itself
 */

fun createImageUri(context: Context): Uri {
    val imageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val imageFile = File(imageDir, "photo_${System.currentTimeMillis()}.jpg")

    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        imageFile
    )
}

suspend fun loadBitmapFromUri(context: Context, uri: Uri, maxSize: Int): Bitmap? =
    withContext(Dispatchers.IO) {
        try {
            context.contentResolver.openInputStream(uri)?.use { input ->
                // First decode bounds only to get original size
                val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
                BitmapFactory.decodeStream(input, null, options)

                val originalWidth = options.outWidth
                val originalHeight = options.outHeight
                val scale = min(maxSize / originalWidth.toFloat(), maxSize / originalHeight.toFloat())

                // Re-open stream for actual decode
                context.contentResolver.openInputStream(uri)?.use { fullInput ->
                    val bmp = BitmapFactory.decodeStream(fullInput)
                    if (bmp != null) {
                        Bitmap.createScaledBitmap(
                            bmp,
                            (bmp.width * scale).toInt(),
                            (bmp.height * scale).toInt(),
                            true
                        )
                    } else null
                }
            }
        } catch (e: Exception) {
            null
        }
    }

