package mg.geit.q64profiler

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHostSetup(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "user_form") {
        composable("user_form") { UserFormScreen(navController) }
        composable("quiz") { QuizScreen(navController) }
        composable("result") { ResultScreen(navController, results = emptyMap()) }
    }
}