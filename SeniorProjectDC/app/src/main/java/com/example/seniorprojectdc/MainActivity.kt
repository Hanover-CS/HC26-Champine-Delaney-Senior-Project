package com.example.seniorprojectdc
/*
This is the main activity file. it sets up the bottom navigation bar, sets the theme,
and starts the app on the home screen
 */
import DetailsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.seniorprojectdc.ui.theme.SeniorProjectDCTheme
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.seniorprojectdc.DB.InsectRepository
import com.example.seniorprojectdc.screens.AddInsectScreen
import com.example.seniorprojectdc.screens.EditScreen
import com.example.seniorprojectdc.screens.HomePage
import com.example.seniorprojectdc.screens.SettingsPage
import com.example.seniorprojectdc.screens.viewScreen


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
                viewScreen(viewModel, navController)
            }
            composable(Screen.AddDb.route) {
                // maybe just reuse InsectScreen or make a separate Add-only screen
                AddInsectScreen(viewModel)
            }
            composable(Screen.Profile.route) { viewScreen(viewModel, navController) }
            composable(Screen.Settings.route) { SettingsPage() }
            composable(
                route = "insect_detail/{insectId}",
                arguments = listOf(navArgument("insectId") { type = NavType.IntType })
            ) { backStackEntry ->
                val insectId = backStackEntry.arguments?.getInt("insectId")
                insectId?.let { id ->
                    val insect = viewModel.getInsectById(id)
                    insect?.let { DetailsScreen(viewModel, it, navController) }
                }
            }
            composable(
                route = "insect_edit/{insectId}",
                arguments = listOf(navArgument("insectId") { type = NavType.IntType })
            ) {
                backStackEntry ->
                val insectId = backStackEntry.arguments?.getInt("insectId")
                insectId?.let { id ->
                    val insect = viewModel.getInsectById(id)
                    insect?.let { EditScreen(viewModel, it, navController) }
                }
            }
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