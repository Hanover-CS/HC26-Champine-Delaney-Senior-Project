package com.example.seniorprojectdc.screens

/*
This screen displays all the database entries in a lazy column. Each entry is clickable
and will bring the user to a details screen for that entry.
 */
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController
import com.example.seniorprojectdc.InsectViewModel

@Composable
fun viewScreen(viewModel: InsectViewModel, navController: NavController) {
    val Insects by viewModel.insects.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(Insects) { insect -> Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp).clickable {
                navController.navigate("insect_detail/${insect.id}")
            },
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(4.dp)
        )
        {
            Column(modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                insect.imageUri?.let {uriString -> val uri = Uri.parse(uriString)
                Image(painter = rememberAsyncImagePainter(uri),
                    contentDescription = insect.insectName,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Text(
                    text = insect.insectName,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Added: ${insect.date}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        }
    }
}