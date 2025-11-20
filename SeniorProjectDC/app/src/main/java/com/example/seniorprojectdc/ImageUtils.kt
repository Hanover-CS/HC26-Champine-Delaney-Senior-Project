package com.example.seniorprojectdc

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun createImageUri(context: Context): Uri {
    val imageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val imageFile = File(imageDir, "photo_${System.currentTimeMillis()}.jpg")

    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        imageFile
    )
}