package com.example.seniorprojectdc.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.seniorprojectdc.Insect
import com.example.seniorprojectdc.InsectViewModel

/*
This screen appears after the user presses the edit button on an insect details screen.
it is similar to the add insect screen, only it is updating an entry, not creating a new one.
you can change anything but the image and then save or discard changes.
 */
@Composable
fun EditScreen(viewModel: InsectViewModel, insect: Insect, navController: NavController){
    var name by remember { mutableStateOf(insect.insectName) }
    var notes by remember { mutableStateOf(insect.notes) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Edit ${insect.insectName}")

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name: ") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes: ") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val updatedInsect = insect.copy(
                    insectName = if (name.isNotBlank()) name else insect.insectName,
                    notes = if (notes.isNotBlank()) notes else insect.notes
                )
                viewModel.updateInsect(updatedInsect)
                navController.popBackStack() // go back after saving
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Edit")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }

}