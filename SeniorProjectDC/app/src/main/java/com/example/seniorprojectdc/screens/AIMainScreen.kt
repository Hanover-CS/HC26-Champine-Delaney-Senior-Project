package com.example.seniorprojectdc.screens
/*
This page gives the user the ability to put an image through the in-app AI model
and see if it can be identified. It displays the AI's prediction and confidence level
 */
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.seniorprojectdc.AIModel
import com.example.seniorprojectdc.InsectViewModel
import com.example.seniorprojectdc.loadBitmapFromUri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.min

@Composable
fun AIMainScreen(viewModel: InsectViewModel, navController: NavController) {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var prediction by remember { mutableStateOf<String?>(null) }
    var confidence by remember { mutableStateOf<Float?>(null) }
    var loading by remember { mutableStateOf(false) }

    val model = remember { AIModel(context) }
    val scope = rememberCoroutineScope()

    // Launcher for selecting image
    val photoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            loading = true
            prediction = null
            confidence = null

            scope.launch {
                try {
                    // Convert URI to bitmap in IO thread
                    val bmp = loadBitmapFromUri(context, it, maxSize = 512)

                    // Update bitmap on main thread
                    bitmap = bmp

                    if (bmp != null) {
                        // Run model in background thread
                        val (label, conf) = withContext(Dispatchers.Default) {
                            model.classify(bmp)
                        }

                        if (conf < 0.5f) {
                            prediction = "Unknown species — try another photo"
                            confidence = null
                        } else {
                            prediction = label
                            confidence = conf
                        }
                    } else {
                        prediction = "Failed to load image"
                    }
                } catch (e: Exception) {
                    prediction = "Error: ${e.message}"
                } finally {
                    loading = false
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Identify Your Discovery with AI", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        Button(onClick = { photoLauncher.launch("image/*") }) {
            Text("Select Photo")
        }

        Spacer(Modifier.height(16.dp))

        if (loading) {
            Text("Analyzing...", color = Color.Gray)
        }

        bitmap?.let {
            Spacer(Modifier.height(16.dp))
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Selected image",
                modifier = Modifier.size(220.dp)
            )
        }

        prediction?.let { label ->
            Spacer(Modifier.height(16.dp))
            Text("Prediction: $label", style = MaterialTheme.typography.titleMedium)
            confidence?.let { conf ->
                Text("Confidence: ${String.format("%.1f", conf * 10)}%", color = Color.Gray)
            }
        }
    }
}

