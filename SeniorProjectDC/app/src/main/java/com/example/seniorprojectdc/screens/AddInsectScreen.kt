package com.example.seniorprojectdc.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seniorprojectdc.InsectViewModel
import com.example.seniorprojectdc.PhotoPickerScreen

/*
This screen allows users to add new objects to the database. there is an option to
add an image, name, description, and there will be a button to have AI tell what the insect is
 */
@Composable
fun AddInsectScreen(viewModel: InsectViewModel) {
    var name by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("")}
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add New Creature",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = { Text("Insect Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = notes,
                onValueChange = {
                    notes = it
                },
                label = { Text("Notes: ") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 📸 Call your PhotoPickerScreen here
            PhotoPickerScreen(onImageSelected = { uri ->
                selectedImageUri = uri
            })

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        viewModel.addInsect(name, selectedImageUri, notes)
                        name = ""
                        notes = ""
                        selectedImageUri = null
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save to Database")
            }
            /* Button(onClick = { viewModel.startAIPrediction() }) {
                Text("Identify with AI")
            }
             */

        }
    }
}