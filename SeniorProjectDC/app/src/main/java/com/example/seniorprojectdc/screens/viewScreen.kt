package com.example.seniorprojectdc.screens

/*
This screen displays all the database entries in a lazy column. Each entry is clickable
and will bring the user to a details screen for that entry.
 */
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.seniorprojectdc.service_classes.InsectViewModel

/*
This is the view screen, on this screen you can see all the database entries laid out
on a lazy column. the entries are clickable and will take you to a details screen of the entry.
 */
@Composable
fun viewScreen(viewModel: InsectViewModel, navController: NavController) {
    val Insects by viewModel.insects.collectAsState()
    var searchText by remember {mutableStateOf("")}
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    viewModel.setSearchQuery(it)
                },
                label = { Text("Search insects") },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            )
            Box {
                OutlinedButton(onClick = { expanded = true }) {
                    Text("Sort")
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("By date") },
                        onClick = {
                            viewModel.setSortMode(InsectViewModel.SortMode.DATE)
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Alphabetical") },
                        onClick = {
                            viewModel.setSortMode(InsectViewModel.SortMode.ALPHABETICAL)
                            expanded = false
                        }
                    )
                }
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(Insects) { insect -> Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp).clickable {
                    navController.navigate("insect_detail/${insect.id}")
                },
                colors = CardDefaults.cardColors(
                    MaterialTheme.colorScheme.surface
                ),
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
}