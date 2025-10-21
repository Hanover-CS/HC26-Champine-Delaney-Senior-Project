package com.example.seniorprojectdc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.seniorprojectdc.ui.theme.SeniorProjectDCTheme


sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object ViewDb : Screen("view_db", "View DB", Icons.AutoMirrored.Filled.List)
    object AddDb : Screen("add_db", "Add DB", Icons.Default.Add)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
}

val bottomNavItems = listOf(
    Screen.Home,
    Screen.ViewDb,
    Screen.AddDb,
    Screen.Profile,
    Screen.Settings
)

@Composable
fun MainScreen(viewModel: InsectViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomePage() }
            composable(Screen.ViewDb.route) {
                // show the database list here
                viewScreen(viewModel)
            }
            composable(Screen.AddDb.route) {
                // maybe just reuse InsectScreen or make a separate Add-only screen
                InsectScreen(viewModel)
            }
            composable(Screen.Profile.route) { ProfilePage() }
            composable(Screen.Settings.route) { SettingsPage() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        bottomNavItems.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) }
            )
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = InsectDatabase.getDatabase(application).insectDao()
        val repository = InsectRepository(dao)
        val factory = InsectViewModelFactory(repository)

        setContent {
            MaterialTheme {
                SeniorProjectDCTheme(darkTheme = false, dynamicColor = false) {
                    val viewModel: InsectViewModel = viewModel(factory = factory)
                    MainScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun InsectScreen(viewModel: InsectViewModel) {
    val scores by viewModel.insects.collectAsState()

    var name by remember { mutableStateOf("") }
    var points by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Player Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = points,
            onValueChange = { points = it },
            label = { Text("Points") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (name.isNotBlank() && points.isNotBlank()) {
                    viewModel.addInsect(name, points.toIntOrNull() ?: 0)
                    name = ""
                    points = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Score", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(scores) { score ->
                Text("${score.insectName}: ${score.date}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun viewScreen(viewModel: InsectViewModel) {
    val scores by viewModel.insects.collectAsState()

    var name by remember { mutableStateOf("") }
    var points by remember { mutableStateOf("") }
    LazyColumn {
        items(scores) { score ->
            Text("${score.insectName}: ${score.date}")
        }
    }
}
