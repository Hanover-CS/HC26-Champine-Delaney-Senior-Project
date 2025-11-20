import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.seniorprojectdc.Insect
import com.example.seniorprojectdc.InsectViewModel

@Composable
fun DetailsScreen(viewModel: InsectViewModel, insect: Insect, navController: NavController) {
    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Row(

        ) {
            Button(onClick = {navController.navigate("insect_edit/${insect.id}")}) {
                Text("Edit")
            }
           Button(onClick = {
               viewModel.deleteInsect(insect)
               navController.popBackStack()
           }) {
               Text("Delete")
           }
        }
        Spacer(modifier = Modifier.height(5.dp))
        insect.imageUri?.let { uriString ->
            val uri = Uri.parse(uriString)
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = insect.insectName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { Text(text = insect.insectName, style = MaterialTheme.typography.headlineSmall) }
            item {
                Text(
                    text = "Added on ${insect.date}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            item {
            Text(text = insect.notes, style = MaterialTheme.typography.headlineSmall)
            }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.popBackStack() }
                ) {
                    Text("Back")
                }
            }
        }

    }

}
