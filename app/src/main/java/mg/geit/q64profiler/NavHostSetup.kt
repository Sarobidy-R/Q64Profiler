package mg.geit.q64profiler

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun NavHostSetup(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "user_form") {
        composable("user_form") { UserFormScreen(navController) }
        composable("quiz") { QuizScreen(navController) }
        composable(
            route = "result/{results}",
            arguments = listOf(navArgument("results") { type = NavType.StringType })
        ) { backStackEntry ->
            val resultsJson = backStackEntry.arguments?.getString("results")
            val results: Map<String, Int> = Gson().fromJson(resultsJson, object : TypeToken<Map<String, Int>>() {}.type)
            ResultScreen(navController = navController, results = results)
        }
    }
}