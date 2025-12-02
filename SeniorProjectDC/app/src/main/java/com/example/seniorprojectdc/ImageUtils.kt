package com.example.seniorprojectdc

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File

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